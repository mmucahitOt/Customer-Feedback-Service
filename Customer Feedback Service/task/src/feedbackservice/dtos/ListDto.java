package feedbackservice.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListDto<T> {

    public ListDto(long totalDocuments, boolean isFirstPage, boolean isLastPage, List<T> documents) {
        this.totalDocuments = totalDocuments;
        this.isFirstPage = isFirstPage;
        this.isLastPage = isLastPage;
        this.documents = documents;
    }

    @JsonProperty("total_documents")
    public long totalDocuments;

    @JsonProperty("is_first_page")
    public boolean isFirstPage;

    @JsonProperty("is_last_page")
    public boolean isLastPage;

    public List<T> documents;
}
