package com.example.yofficial;



public class CommentItem  {

    String user_id;
    String upload_date;
    String comment_data;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }

    public String getComment_data() {
        return comment_data;
    }

    public void setComment_data(String comment_data) {
        this.comment_data = comment_data;
    }


    public CommentItem ( String  _user_id, String _upload_date, String _comment_data){
        user_id = _user_id;
        upload_date = _upload_date;
        comment_data = _comment_data;
    }


}
