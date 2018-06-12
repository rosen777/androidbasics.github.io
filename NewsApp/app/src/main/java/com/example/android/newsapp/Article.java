package com.example.android.newsapp;

public class Article {

    /**
     * {@link Article} represents a single article object.
     * Each object has 4 properties: title, section, date, and url.
     */

    // Title of the article (e.g. Brexit article)
    private String mTitle;

    // Section of the article (e.g. political)
    private String mSection;

    // Date of the article (e.g. 11 June 2018, 20:44:30)
    private String mDate;

    // URL of the article (e.g. https://www.theguardian.com/business/2018/may/29/brexit-disaster-economic-data-uk-eu)
    private String mUrl;

    public Article(String Title, String Section, String Date, String Url) {
        mTitle = Title;
        mSection = Section;
        mDate = Date;
        mUrl = Url;
    }

    /**
     * Get the title of the article
     */

    public String getTitle() {
        return mTitle;
    }

    /**
     * Get the section of the article
     */

    public String getSection(){
        return mSection;
    }

    /**
     * Get the date of the article
     */

    public String getDate() {
        return mDate;
    }

    /**
     * Get the URL at The Guardian
     */

    public String getUrl() {
        return mUrl;
    }

}
