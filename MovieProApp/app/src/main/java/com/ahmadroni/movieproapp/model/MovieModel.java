package com.ahmadroni.movieproapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;
import javax.validation.Valid;

@Generated("jsonschema2pojo")
public class MovieModel extends BaseObservable implements Parcelable {
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("genre_ids")
    @Expose
    @Valid
    private List<Integer> genreIds;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;

    public static final Parcelable.Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel parcel) {
            return new MovieModel(parcel);
        }

        @Override
        public MovieModel[] newArray(int i) {
            return new MovieModel[i];
        }
    };

    public MovieModel() {

    }

    public MovieModel(Parcel parcel) {
        this.adult = ((Boolean) parcel.readValue(Boolean.class.getClassLoader()));
        this.video = ((Boolean) parcel.readValue(Boolean.class.getClassLoader()));

        this.backdropPath = ((String) parcel.readValue(String.class.getClassLoader()));
        this.originalLanguage = ((String) parcel.readValue(String.class.getClassLoader()));
        this.originalTitle = ((String) parcel.readValue(String.class.getClassLoader()));
        this.overview = ((String) parcel.readValue(String.class.getClassLoader()));
        this.posterPath = ((String) parcel.readValue(String.class.getClassLoader()));
        this.releaseDate = ((String) parcel.readValue(String.class.getClassLoader()));
        this.title = ((String) parcel.readValue(String.class.getClassLoader()));

        this.id = ((Integer) parcel.readValue(Integer.class.getClassLoader()));
        this.voteCount = ((Integer) parcel.readValue(Integer.class.getClassLoader()));

        this.popularity = ((Double) parcel.readValue(Double.class.getClassLoader()));
        this.voteAverage = ((Double) parcel.readValue(Double.class.getClassLoader()));
        //parcel.readList(this.genreIds, (Integer.class.getClassLoader()));
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @BindingAdapter({"posterPath"})
    public static void loadImage(ImageView imageView, String imageURL){
        String imagePath = "https://image.tmdb.org/t/p/w500"+imageURL;

        Glide.with(imageView.getContext())
                .load(imagePath)
                .into(imageView);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeValue(this);
        parcel.writeValue(this.adult);
        parcel.writeValue(this.backdropPath);
        //parcel.writeValue(this.genreIds);
        parcel.writeValue(this.id);
        parcel.writeValue(this.originalLanguage);
        parcel.writeValue(this.originalTitle);
        parcel.writeValue(this.overview);
        parcel.writeValue(this.popularity);
        parcel.writeValue(this.posterPath);
        parcel.writeValue(this.releaseDate);
        parcel.writeValue(this.title);
        parcel.writeValue(this.video);
        parcel.writeValue(this.voteAverage);
        parcel.writeValue(this.voteCount);
    }
}
