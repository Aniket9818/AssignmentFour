package com.e.hamrobazar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Term extends AppCompatActivity {
    CheckBox cTerms, cSafety, cRules;
    Button btnAgreement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);


        cTerms = findViewById(R.id.cTerms);
        cSafety = findViewById(R.id.cSafety);
        cRules = findViewById(R.id.cRules);
        btnAgreement = findViewById(R.id.btnAgreement);
        final Handler handler = new Handler();


        cTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cTerms.isChecked()) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Term.this, TermOne.class);
                            startActivity(intent);
                        }
                    }, 50);
                } else {
                    cTerms.setChecked(true);
                }
            }
        });


        cSafety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cSafety.isChecked()) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Term.this, TermThree.class);
                            startActivity(intent);
                        }
                    }, 50);
                } else {
                    cSafety.setChecked(true);
                }
            }
        });

        cRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cRules.isChecked()) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Term.this, TermTwo.class);
                            startActivity(intent);
                        }
                    }, 50);

                } else {
                    cRules.setChecked(true);
                }
            }
        });

        btnAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cTerms.isChecked() && cSafety.isChecked() && cRules.isChecked()) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Term.this, Dashboard.class);
                            startActivity(intent);
                            Agreement();
                            finish();
                        }


                    }, 300);
                } else if (!cTerms.isChecked()) {
                    dialogboxTerms();
                } else if (!cSafety.isChecked()) {
                    dialogboxSafety();
                } else if (!cRules.isChecked()) {
                    dialogboxRules();
                }
            }


        });
    }

            private void Agreement() {
                SharedPreferences sharedPreferences = getSharedPreferences("userterms", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Terms", "true");
                editor.putString("Safety", "true");
                editor.putString("AdsRules", "true");
                editor.commit();
            }

            private void dialogboxTerms() {
                new AlertDialog.Builder(Term.this)
                        .setTitle("Terms of use")
                        .setMessage("You have not read Terms of Use. Please click above button to view it.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton(null, null)
                        .setCancelable(false)
                        .show();
            }
            private void dialogboxRules() {
                new AlertDialog.Builder(Term.this)
                        .setTitle("Ad posting Rule")
                        .setMessage("You have not read Ad Posting Rule. Please click above button to view it.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton(null, null)
                        .setCancelable(false)
                        .show();
            }

            private void dialogboxSafety() {
                new AlertDialog.Builder(Term.this)
                        .setTitle("Safety Tips")
                        .setMessage("You have not read Safety Tips. Please click above button to view it.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton(null, null)
                        .setCancelable(false)
                        .show();

    }
}
