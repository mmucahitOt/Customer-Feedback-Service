package feedbackservice;

import feedbackservice.dtos.ListDto;
import feedbackservice.entity.Feedback;
import feedbackservice.repository.FeedbackRepository;
import feedbackservice.validationconfig.beans.PaginationConfig;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FeedbackController {
    FeedbackRepository repository;

    FeedbackController(FeedbackRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/feedback/{id}")
    public ResponseEntity<Feedback> findById(@PathVariable("id") String id) {
        Optional<Feedback> result = this.repository.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            Feedback feedback = result.get();
            return ResponseEntity.status(HttpStatus.OK).body(feedback);
        }
    }

    @GetMapping("/feedback")
    public ListDto<Feedback> findAll(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "perPage", required = false) Integer perPage,
            @RequestParam(name = "rating", required = false) Integer rating,
            @RequestParam(name = "customer", required = false) String customer,
            @RequestParam(name = "product", required = false) String product,
            @RequestParam(name = "vendor", required = false) String vendor
    ) {
        PaginationConfig paginationConfig = new PaginationConfig(page, perPage);

        Feedback feedback = new Feedback();
        feedback.setRating(rating);
        feedback.setCustomer(customer);
        feedback.setProduct(product);
        feedback.setVendor(vendor);

        Example<Feedback> example = Example.of(feedback);

        Pageable pageable = PageRequest.of(paginationConfig.getPage(), paginationConfig.getPerPage(), Sort.by(Sort.Direction.DESC, "_id"));
        Page<Feedback> result = repository.findAll(example, pageable);
        List<Feedback> feedbacks = result.getContent();

        return new ListDto<Feedback>(result.getTotalElements(), result.isFirst(), result.isLast(), feedbacks);
    }

    @PostMapping("/feedback")
    public ResponseEntity<Feedback> postMyEntity(@RequestBody Feedback feedback) {
        Feedback createdFeedback = this.repository.insert(feedback);

        String headerName = "Location";
        String valueOfLocationHeader = "/feedback/" + feedback.getId();

        return ResponseEntity.status(HttpStatus.CREATED).header(headerName, valueOfLocationHeader).body(createdFeedback);
    }
}