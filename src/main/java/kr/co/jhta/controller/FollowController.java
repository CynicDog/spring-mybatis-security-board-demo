package kr.co.jhta.controller;

import kr.co.jhta.security.model.SecurityUser;
import kr.co.jhta.service.BoardService;
import kr.co.jhta.vo.FollowsRequest;
import org.jboss.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final Logger logger = Logger.getLogger(FollowController.class);

    private final BoardService boardService;
    public FollowController(BoardService boardService) {
        this.boardService = boardService;
    }

    // TODO: securing access only to authenticated users
    @GetMapping("/request")
    public ResponseEntity request(
                @AuthenticationPrincipal SecurityUser user,
                @RequestParam("recipient-id") int recipientId) {

        try {
            boardService.queueRequest(user.getUser().getId(), recipientId);
            return ResponseEntity.ok("Success");
        } catch (RuntimeException e) {
            logger.error("Error processing follow request: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/accept")
    public ResponseEntity accept(
            @RequestParam("sender-id") int senderId,
            @AuthenticationPrincipal SecurityUser user) {

        try {
            boardService.acceptRequest(senderId, user.getUser().getId());
            return ResponseEntity.ok("Success");
        } catch (RuntimeException e) {
            logger.error("Error processing follow request: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/decline")
    public ResponseEntity decline() {

        return null;
    }
}
