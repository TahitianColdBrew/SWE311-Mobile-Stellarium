package org.xmum.stellarium.utils;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;

import org.xmum.stellarium.MyCompleteListener;
import org.xmum.stellarium.model.CategoryModel;
import org.xmum.stellarium.model.QuestionModel;
import org.xmum.stellarium.model.RankModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbQuery {

    public static int g_selectedCatIndex = -1;
    public static FirebaseFirestore g_firestore;
    public static List<CategoryModel> g_catList = new ArrayList<>();
    public static List<QuestionModel> g_questionList = new ArrayList<>();

    public static RankModel myPerformance = new RankModel(0, -1);

    public static void loadMyScores(MyCompleteListener completeListener){
        g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getUid())
                .collection("USER_DATA").document("MY_SCORES")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        for(int i = 0;i < g_catList.size();i++){
                            int top = 0;
                            if(documentSnapshot.get(g_catList.get(i).getCid()) != null){
                                top = documentSnapshot.getLong(g_catList.get(i).getCid()).intValue();
                            }
                            g_catList.get(i).setBestScore(top);
                        }
                        completeListener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        completeListener.onFailure();
                    }
                });
    }

    public static void saveResult(int score, MyCompleteListener completeListener) {
        WriteBatch batch = g_firestore.batch();

        DocumentReference userDoc = g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getUid());

        batch.update(userDoc, "TOTAL_SCORE", score);

        if (score > g_catList.get(g_selectedCatIndex).getBestScore()) {
            DocumentReference scoreDoc = userDoc.collection("USER_DATA").document("MY_SCORES");

            Map<String, Object> testData = new HashMap<>();
            testData.put(g_catList.get(g_selectedCatIndex).getCid(), score);

            batch.set(scoreDoc, testData, SetOptions.merge());
        }

        batch.commit()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        if (score > g_catList.get(g_selectedCatIndex).getBestScore()) {
                            g_catList.get(g_selectedCatIndex).setBestScore(score);
                        }
                        myPerformance.setScore(score);
                        completeListener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        completeListener.onFailure();
                    }
                });
    }

    public static void createUserData(String email, String name, MyCompleteListener completeListener) {
        Map<String, Object> user = new HashMap<>();
        user.put("EMAIL", email);
        user.put("NAME", name);
        user.put("TOTAL_SCORE", 0);

        DocumentReference userDoc = g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        WriteBatch batch = g_firestore.batch();
        batch.set(userDoc, user);

        DocumentReference countDoc = g_firestore.collection("USERS").document("TOTAL_USERS");
        batch.update(countDoc, "COUNT", FieldValue.increment(1));

        batch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                completeListener.onSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                completeListener.onFailure();
            }
        });
    }

    public static void loadCategories(MyCompleteListener completeListener) {
        g_catList.clear();

        g_firestore.collection("QUIZ").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Map<String, QueryDocumentSnapshot> docList = new HashMap<>();
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    docList.put(doc.getId(), doc);
                }
                QueryDocumentSnapshot catListDoc = docList.get("Categories");

                long catCount = catListDoc.getLong("COUNT");
                for (int i = 1; i <= catCount; i++) {
                    String catID = catListDoc.getString("CAT" + i + "_ID");

                    QueryDocumentSnapshot catDoc = docList.get(catID);

                    CategoryModel categoryModel = new CategoryModel();
                    categoryModel.setCid(catDoc.getString("CAT_ID"));
                    categoryModel.setName(catDoc.getString("NAME"));
                    categoryModel.setUrl(catDoc.getString("IMG_URL"));
                    categoryModel.setNoOfQuestions(catDoc.getLong("NO_OF_QUESTIONS").intValue());
                    categoryModel.setTime(catDoc.getLong("TIME").intValue());

                    g_catList.add(categoryModel);
                }

                completeListener.onSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                completeListener.onSuccess();
            }
        });
    }

    public static void loadQuestions(MyCompleteListener completeListener) {
        g_questionList.clear();

        g_firestore.collection("QUESTIONS")
                .whereEqualTo("CATEGORY", g_catList.get(g_selectedCatIndex).getCid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot doc : queryDocumentSnapshots) {
                            g_questionList.add(new QuestionModel(
                                    doc.getString("QUESTION"),
                                    doc.getString("A"),
                                    doc.getString("B"),
                                    doc.getString("C"),
                                    doc.getString("D"),
                                    doc.getLong("ANSWER").intValue()
                            ));
                        }

                        completeListener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        completeListener.onFailure();
                    }
                });
    }

}
