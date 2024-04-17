package com.example.expensemanager;

import android.graphics.Bitmap;
import android.net.Uri;

public class Friend {
    String firstname;
    String lastname;
    Uri profilepic;
    String email;
    String instaid;
    String phoneno, whatsappno;
    String birthdate;
    String address;

    String hobbies;
    String favcolor, favmovie, favmusician;

    int profilepic2;
    Bitmap bitmap_profilepic;

    public Friend(Uri profilepic, String firstname, String lastname, String birthdate, String phoneno, String whatsappno, String email, String address, String instaid, String hobbies, String favmusician, String favcolor, String favmovie) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.profilepic = profilepic;
        this.email = email;
        this.instaid = instaid;
        this.phoneno = phoneno;
        this.whatsappno = whatsappno;
        this.birthdate = birthdate;
        this.address = address;
        this.hobbies = hobbies;
        this.favcolor = favcolor;
        this.favmovie = favmovie;
        this.favmusician = favmusician;
    }

    public Friend(int profilepic2, String firstname, String lastname, String birthdate, String phoneno, String whatsappno, String email, String address, String instaid, String hobbies, String favmusician, String favcolor, String favmovie) {
        this.profilepic2 = profilepic2;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.phoneno = phoneno;
        this.whatsappno = whatsappno;
        this.email = email;
        this.address = address;
        this.instaid = instaid;
        this.hobbies = hobbies;
        this.favmusician = favmusician;
        this.favcolor = favcolor;
        this.favmovie = favmovie;

    }

    public Friend(Bitmap bitmap_profilepic, String firstname, String lastname, String birthdate, String phoneno, String whatsappno, String email, String address, String instaid, String hobbies, String favmusician, String favcolor, String favmovie) {
        this.bitmap_profilepic = bitmap_profilepic;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.phoneno = phoneno;
        this.whatsappno = whatsappno;
        this.email = email;
        this.address = address;
        this.instaid = instaid;
        this.hobbies = hobbies;
        this.favmusician = favmusician;
        this.favcolor = favcolor;
        this.favmovie = favmovie;

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Uri getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(Uri profilepic) {
        this.profilepic = profilepic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstaid() {
        return instaid;
    }

    public void setInstaid(String instaid) {
        this.instaid = instaid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getWhatsappno() {
        return whatsappno;
    }

    public void setWhatsappno(String whatsappno) {
        this.whatsappno = whatsappno;
    }

    public String getBirthdate() {
        return birthdate;
    }


//SETTERS

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getFavcolor() {
        return favcolor;
    }

    public void setFavcolor(String favcolor) {
        this.favcolor = favcolor;
    }

    public String getFavmovie() {
        return favmovie;
    }

    public void setFavmovie(String favmovie) {
        this.favmovie = favmovie;
    }

    public String getFavmusician() {
        return favmusician;
    }

    public void setFavmusician(String favmusician) {
        this.favmusician = favmusician;
    }

    public int getProfilepic2() {
        return profilepic2;
    }

    public void setProfilepic2(int profilepic2) {
        this.profilepic2 = profilepic2;
    }

    public Bitmap getBitmap_profilepic() {
        return bitmap_profilepic;
    }

    public void setBitmap_profilepic(Bitmap bitmap_profilepic) {
        this.bitmap_profilepic = bitmap_profilepic;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", profilepic=" + profilepic +
                ", email='" + email + '\'' +
                ", instaid='" + instaid + '\'' +
                ", phoneno='" + phoneno + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", address='" + address + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", favcolor='" + favcolor + '\'' +
                ", favmovie='" + favmovie + '\'' +
                ", favmusician='" + favmusician + '\'' +
                '}';
    }

}