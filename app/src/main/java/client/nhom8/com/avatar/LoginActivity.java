package client.nhom8.com.avatar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by TooNies1810 on 11/26/15.
 */
public class LoginActivity extends Activity {

    private static final int REQUEST_CODE_REGISTER = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }

    private void initViews() {
        final EditText edtUsername = (EditText) findViewById(R.id.edt_username);
        final EditText edtPass= (EditText) findViewById(R.id.edt_password);

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        Button btnRegister = (Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(mIntent, REQUEST_CODE_REGISTER);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin(edtUsername.getText().toString(), edtPass.getText().toString());
            }
        });
    }

    //request username va pass len server de su li
    //Neu user dung thi luu user vao sqlite, luu session, chuyen den MainActivity
    private void checkLogin(String username, String pass) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_REGISTER){
            if (resultCode == RESULT_OK){
                // dien thong tin vua dang ki vao form

            } else if (resultCode == RESULT_CANCELED){
                // neu ket qua tra ve la cancel thi khong lam gi ca
                return;
            }
        }
    }
}
