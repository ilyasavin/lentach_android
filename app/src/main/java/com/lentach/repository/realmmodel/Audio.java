
package com.lentach.repository.realmmodel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Audio {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("genre_id")
    @Expose
    private Integer genreId;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The ownerId
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * 
     * @param ownerId
     *     The owner_id
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 
     * @return
     *     The artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * 
     * @param artist
     *     The artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration
     *     The duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * 
     * @return
     *     The date
     */
    public Integer getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(Integer date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The genreId
     */
    public Integer getGenreId() {
        return genreId;
    }

    /**
     * 
     * @param genreId
     *     The genre_id
     */
    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

}
