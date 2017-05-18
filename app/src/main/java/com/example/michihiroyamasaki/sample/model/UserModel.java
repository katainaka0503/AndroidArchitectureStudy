package com.example.michihiroyamasaki.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.michihiroyamasaki.sample.R;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザを表すモデルです。
 *
 * ユーザはユーザID、パスワード、性別、都道府県、誕生日の情報を持ちます。
 */
@DatabaseTable
@Data
@NoArgsConstructor
public class UserModel implements Parcelable {
    @DatabaseField(id=true)
    private String userId;
    @DatabaseField
    private String password;
    @DatabaseField
    private Gender gender;
    @DatabaseField
    private String prefecture;
    @DatabaseField
    private Date birthDay;


    /**
     * ユーザの情報のバリデーションを行い、エラーのリストを返します。
     *
     * @return エラーメッセージのIDのリスト
     */
    public List<Integer> validate() {
        List<Integer> errors = new ArrayList<>();
        if (userId == null || userId.isEmpty()) {
            errors.add(R.string.userid_empty);
        }
        if (password == null || password.isEmpty()){
            errors.add(R.string.password_empty);
        }
        if(gender == null) {
            errors.add(R.string.gender_empty);
        }
        if(prefecture == null || prefecture.isEmpty()){
            errors.add(R.string.prefecture_empty);
        }
        return errors;
    }

    /**
     * 性別を表わす列挙型です。
     */
    public enum Gender {
        Male, Female
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.password);
        dest.writeInt(this.gender == null ? -1 : this.gender.ordinal());
        dest.writeString(this.prefecture);
        dest.writeLong(this.birthDay != null ? this.birthDay.getTime() : -1);
    }

    protected UserModel(Parcel in) {
        this.userId = in.readString();
        this.password = in.readString();
        int tmpGender = in.readInt();
        this.gender = tmpGender == -1 ? null : Gender.values()[tmpGender];
        this.prefecture = in.readString();
        long tmpBirthDay = in.readLong();
        this.birthDay = tmpBirthDay == -1 ? null : new Date(tmpBirthDay);
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
