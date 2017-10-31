
/*
*   Copyright (c) Ashar Khan 2017. <ashar786khan@gmail.com>
*    This file is part of Google Developer Group's Android Application.
*   Google Developer Group 's Android Application is free software : you can redistribute it and/or modify
*    it under the terms of GNU General Public License as published by the Free Software Foundation,
*   either version 3 of the License, or (at your option) any later version.
*
*   This Application is distributed in the hope that it will be useful
*    but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
*   or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General  Public License for more details.
*
*   You should have received a copy of the GNU General Public License along with this Source File.
*   If not, see <http:www.gnu.org/licenses/>.
 */


package com.softminds.gdg.utils;


import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

//This suppress is required because we will not use any setters for this class
// but we need them because fire base internally use these setters
@SuppressWarnings("unused")
public class AppUsers {

    public static final int ADMIN_ACCESS = 122;
    public static final int MEMBER_ACCESS = 120;
    public static final int PUBLIC_ACCESS = 121;

    public static final int GENDER_MALE = 0;
    public static final int GENDER_FEMALE = 1;

    //Technical SKills

    public static final int ANDROID_DEVELOPMENT = 100;
    public static final int WEB_DEVELOPMENT = 101;
    public static final int GOOGLE_CLOUD = 102;
    public static final int TENSOR_FLOW = 103;
    public static final int FIRE_BASE_DEVELOPMENT = 104;

    //Non Technical Skills
    public static final int PUBLIC_SPEAKING = 150;
    public static final int PROMOTION_SKILLS = 151;



    //data-fields this will be used in a key value pairs in decomposition of this class
    private String name; // we take this as username
    private String email;
    private String authUid;
    private String picUrl;
    private String fcmToken;
    private int AccessLevel;
    private long MemberSince;
    private long LastSeen;
    private String position;
    private int gender;
    private String aboutMe;
    private int[]  Skills;
    @Deprecated
    private String otherSkills;
    private List<PersonalMessage> personalMessages;



    public AppUsers(){
        //requires empty constructor for real time database
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public long getLastSeen() {
        return LastSeen;
    }

    public String getOtherSkills() {
        return otherSkills;
    }

    public boolean hasOtherSkills(){
        return this.otherSkills !=null && !this.otherSkills.isEmpty();
    }

    public int getAccessLevel() {
        return AccessLevel;
    }

    public int getGender() {
        return gender;
    }

    public String getAuthUid() {
        return authUid;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public long getMemberSince() {
        return MemberSince;
    }

    public int[] getSkills() {
        return Skills;
    }

    public String getPosition() {
        return position;
    }

    public List<PersonalMessage> getPersonalMessages() {
        return personalMessages;
    }

    public String getPicUrl() {
        return picUrl;
    }


    public void setAuthUid(String authUid) {
        this.authUid = authUid;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMemberSince(long memberSince) {
        MemberSince = memberSince;
    }

    public void setAccessLevel(int Access) {
        this.AccessLevel = Access;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSkills(int[] skills) {
        Skills = skills;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setPersonalMessages(List<PersonalMessage> personalMessages) {
        this.personalMessages = personalMessages;
    }

    public void setLastSeen(long lastSeen) {
        LastSeen = lastSeen;
    }

    /*
        We use this token to generate the unique user node under database
        this token will be unique for all users
        spaces are not allowed in emails but spaces can be used as a key so
        we replace all chars that does not support to be a qualified key in database
        with spaces and result is a unique key,
        we may use uid of auth as key but i have noticed it changing as a new account
        with old email again after deletion, so we stick to this method
          */
    @SuppressWarnings("ConstantConditions")
    public static String generateAppUserToken(){
        if(FirebaseAuth.getInstance().getCurrentUser() != null
                && FirebaseAuth.getInstance().getCurrentUser().getEmail() !=null){

            return FirebaseAuth.getInstance().getCurrentUser().getEmail()
                    .replace('.', ' ')
                    .replace('@', ' ');
        }
        return null;
    }

    public static String generateAppUserTokenWithEmail(String Email) {
         if(Email.contains("@") && Email.contains("."))
            return Email.replace('.',' ').replace('@',' ');
        else
            return null;
    }

    @SuppressWarnings("ConstantConditions")
    public static AppUsers defaultUser() {
        AppUsers appUsers = new AppUsers();
        appUsers.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        appUsers.setAuthUid(FirebaseAuth.getInstance().getUid());
        appUsers.setLastSeen(System.currentTimeMillis());
        appUsers.setMemberSince(System.currentTimeMillis());
        appUsers.setAccessLevel(AppUsers.PUBLIC_ACCESS);
        appUsers.setPicUrl(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString());
        return appUsers;
    }

    public static class PersonalMessage{
        private String body;
        private long timestamp;
        private String senderName;
        private String senderProfileReference;

        PersonalMessage(){

        }

        public long getTimestamp() {
            return timestamp;
        }

        public String getBody() {
            return body;
        }

        public String getSenderName() {
            return senderName;
        }

        public String getSenderProfileReference() {
            return senderProfileReference;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

        public void setSenderProfileReference(String senderProfileReference) {
            this.senderProfileReference = senderProfileReference;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }
}