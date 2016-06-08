package hyp.my060801;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 数据篇之SharedPreferences
 * 一种轻型的数据存储方式
 * 本质是基于xml文件存储key-value键值对数据
 * 通常用来存储一些简单地配置信息
 * <p/>
 * <p/>
 * SharedPreferences本身只能获取数据不支持存储和修改，修改、存储是Editor来实现
 * 步骤：1获取SharedPreferences对象
 * 2获得SharedPreferences.Editor对象
 * 3通过Editor接口的putXxx方法来保存key-value对，其中的Xxx表示不同的数据类型
 * 4通过Editor接口的commit来提交来保存key-value值
 */
public class LoginActivity extends AppCompatActivity {
    private EditText etUserName, etUserPwd;
    private CheckBox chk;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in);
        etUserName = (EditText) findViewById(R.id.et_username);
        etUserPwd = (EditText) findViewById(R.id.et_userpwd);
        chk = (CheckBox) findViewById(R.id.chkSaveName);
        SharedPreferences pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor = pref.edit();
        String name = pref.getString("userName","");
        if(name == null){
            chk.setChecked(false);
        }else{
            chk.setChecked(true);
            etUserName.setText(name);
        }
    }

    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login: {
                String name = etUserName.getText().toString().trim();
                String password = etUserPwd.getText().toString().trim();
                if ("890vip".equals(name) && "890vip".equals(password)) {
                    if(chk.isChecked()){
                        editor.putString("userName",name);
                        editor.commit();
                    }else{
                        editor.remove("userName");
                        editor.commit();
                    }
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.bt_cancel: {
                break;
            }
        }

    }
}
