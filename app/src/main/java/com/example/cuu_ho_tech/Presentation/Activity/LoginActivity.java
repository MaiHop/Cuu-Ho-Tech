package com.example.cuu_ho_tech.Presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cuu_ho_tech.Presentation.Activity.Register.RegisterActivity;
import com.example.cuu_ho_tech.Presentation.Activity.ResetPassword.ResetPasswordAccountActivity;
import com.example.cuu_ho_tech.Presentation.Dialog.CustomDialog;
import com.example.cuu_ho_tech.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
//    GoogleSignInOptions googleSignInOptions;
//    GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {
//        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);


        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEdtEmpty()) {
                    CustomDialog customDialog = new CustomDialog().setLoading(true);
                    customDialog.show(getSupportFragmentManager(), "Dialog");
                    Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            customDialog.cancel();
                            showDialogSuccess();
                        }
                    };
                    handler.postDelayed(runnable, 5000);
                }
            }
        });
        binding.edtPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.edtPasswordLayout.setError(null);
            }
        });
        binding.edtPhone.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.edtPhoneLayout.setError(null);
            }
        });
        binding.btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGoogleAccounts();
            }
        });
        binding.btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordAccountActivity.class);
                startActivity(intent);
            }
        });
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }



//    private void pickEmail() {
//        Intent sigInIntent = googleSignInClient.getSignInIntent();
//        startActivityForResult(sigInIntent, 1000);
//    }

    public void getGoogleAccounts() {
//        AccountManager accountManager = AccountManager.get(this);
//        Account[] accounts = accountManager.getAccountsByType("com.google");
//
//        for (Account account : accounts) {
//            String email = account.name; // Lấy địa chỉ email của tài khoản
//            Log.d("Accountss", "Email: " + email);
//        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1000) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
//                if(googleSignInAccount != null) {
//                    String email = googleSignInAccount.getEmail();
//                    Log.d("AAA", "onActivityResult: " + email);
//                    Toast.makeText(LoginActivity.this, "Email: " + email, Toast.LENGTH_SHORT).show();
//                }
//                task.getResult(ApiException.class);
//            } catch (ApiException e) {
//                Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
//                Log.d("AAA", "onActivityResult: " + e);
//            }
//        } else {
//            Log.d("AAA", "Intent data is null or result is not OK");
//        }
//    }


    private void showDialogSuccess() {
        CustomDialog dialog = new CustomDialog()
                .setType(CustomDialog.SUCCESS)
                .setTitle("Đăng nhập thành công")
                .setText("Chào mừng quay trở lại Rescue")
                .setIconLoading(true);
        dialog.show(getSupportFragmentManager(), "CustomDialog");
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                dialog.cancel();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };
        handler.postDelayed(runnable, 5000);
    }

    private boolean checkEdtEmpty() {
        boolean isValid  = true;
        if(binding.edtPhone.getText().toString().isEmpty()) {
            binding.edtPhoneLayout.setError("Email không được để trống!");
            binding.edtPhoneLayout.clearFocus();
            isValid = false;
        }
        if(binding.edtPassword.getText().toString().isEmpty()) {
            binding.edtPasswordLayout.setError("Password không được để trống!");
            binding.edtPasswordLayout.clearFocus();
            isValid = false;
        }
        return isValid;
    }
}