package hyp.my060801;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public class SQLLite extends AppCompatActivity{

    public void nativeSql(){
        /**
         * 数据篇之SQLLite 一
         * 每个程序都有自己的数据库，默认下是各自互不干扰
         */
        //创建一个数据库 并打开
        SQLiteDatabase db = openOrCreateDatabase("user.db",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS user(_id integer primary key autoincrement,name text not null,age integer not null,sex text not null)");
        db.execSQL("INSERT INTO user (name,sex,age) VALUES ('890vip','男','28')");
        db.execSQL("INSERT INTO user (name,sex,age) VALUES ('890vip.cn','女','32')");
        Cursor c = db.rawQuery("SELECT * FROM user",null);
        if(c != null){
            while (c.moveToNext()){
                Log.i("TAG", "_id" + c.getInt(c.getColumnIndex("_id")));
                Log.i("TAG", "name" + c.getString(c.getColumnIndex("name")));
                Log.i("TAG", "sex" + c.getString(c.getColumnIndex("sex")));
                Log.i("TAG", "age" + c.getInt(c.getColumnIndex("age")));
            }
            c.close();
        }
        db.execSQL("DELETE FROM user");
        db.close();
    }
    public void contentValue(){
        /**
         * 数据篇之SQLLite 二
         * ContentValues
         */
        SQLiteDatabase db = openOrCreateDatabase("stu.db",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS stu (_id integer primary key autoincrement,name text not null,sex text not null,age integer not null)");
        ContentValues values = new ContentValues();
        values.put("name","张三");
        values.put("age",60);
        values.put("sex","男");
        db.insert("stu",null,values);
        values.clear();
        values.put("name","张三丰");
        values.put("age",600);
        values.put("sex","男");
        long row = db.insert("stu",null,values);
        Log.i("ROW", "ROW: " + row);
        values.clear();
        values.put("age",900);
        db.update("stu",values,"_id = ?",new String[]{"1"});
        //db.delete("stu","name like ? ",new String[]{"%丰%"});
        Cursor c = db.query("stu",null,"_id > ?",new String[]{"0"},null,null,"age");
        if(c != null){
            String [] columns = c.getColumnNames();
            while (c.moveToNext()){
                for(String columnsName:columns){
                    Log.i("TAG", c.getString(c.getColumnIndex(columnsName)));
                }
            }
            c.close();
        }
        db.close();
    }
}
