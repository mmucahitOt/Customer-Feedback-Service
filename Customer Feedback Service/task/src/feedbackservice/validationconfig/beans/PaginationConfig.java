package feedbackservice.validationconfig.beans;


import jakarta.annotation.PostConstruct;

import java.util.Objects;

public class PaginationConfig {

    static final private int DefaultPageParam = 1;
    static final private int DefaultPerPageParam = 10;
    static final private int MinPageParam = 1;
    static final private int MinPerPageParam = 5;
    static final private int MaxPerPagePram = 20;

    private int page;

    private int perPage;

    private int pageParam;

    private int perPageParam;

    public PaginationConfig(Integer pageParam, Integer perPageParam) {

        this.validatePagination(pageParam, perPageParam);

        this.page = this.pageParam - 1;
        this.perPage = this.perPageParam;
    }

    public int getPage() {
        return this.page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void validatePagination(Integer pageParam, Integer perPageParam) {
        if (pageParam == null) {
            this.pageParam = DefaultPageParam;
        } else {
            if (pageParam < 1) {
                this.pageParam = 1;
            } else {
                this.pageParam = pageParam;
            }
        }

        if (perPageParam == null) {
            this.perPageParam = DefaultPerPageParam;
        }
        else if (perPageParam < MinPerPageParam || perPageParam > MaxPerPagePram) {
            this.perPageParam = DefaultPerPageParam;
        } else
            this.perPageParam = perPageParam;
    }
}
