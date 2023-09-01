package com.ahmadroni.movieproapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Observable;

import javax.annotation.Generated;
import javax.validation.Valid;

@Generated("jsonschema2pojo")
public class MovieResult extends Observable implements Parcelable {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    @Valid
    private List<MovieModel> results;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    public final static Parcelable.Creator<MovieResult> CREATOR = new Creator<MovieResult>() {
        @Override
        public MovieResult createFromParcel(Parcel parcel) {
            return new MovieResult(parcel);
        }

        @Override
        public MovieResult[] newArray(int i) {
            return (new MovieResult[i]);
        }
    };

    public MovieResult() {
    }

    public MovieResult(Parcel parcel) {
        this.page = ((Integer) parcel.readValue(Integer.class.getClassLoader()));
        this.totalPages = ((Integer) parcel.readValue(Integer.class.getClassLoader()));
        this.totalResults = ((Integer) parcel.readValue(Integer.class.getClassLoader()));
        parcel.readList(this.results, (com.ahmadroni.movieproapp.model.MovieModel.class.getClassLoader()));
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<MovieModel> getResults() {
        return results;
    }

    public void setResults(List<MovieModel> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeValue(this.page);
        parcel.writeList(this.results);
        parcel.writeValue(this.totalPages);
        parcel.writeValue(this.totalResults);
    }
}
