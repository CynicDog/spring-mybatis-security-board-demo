package kr.co.jhta.controller;

import kr.co.jhta.security.model.SecurityUser;
import kr.co.jhta.service.BoardService;
import kr.co.jhta.vo.User;
import org.jboss.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
public class FriendController {

    private final Logger logger = Logger.getLogger(FriendController.class);

    private final BoardService boardService;
    public FriendController(BoardService boardService) {
        this.boardService = boardService;
    }

    // TODO: securing access only to authenticated users
    @GetMapping("/request")
    public ResponseEntity request(
                @AuthenticationPrincipal SecurityUser user,
                @RequestParam("recipient-id") int recipientId
            ) {

        try {
            boardService.queueRequest(user.getUser().getId(), recipientId);
            return ResponseEntity.ok("Success");
        } catch (RuntimeException e) {
            logger.error("Error processing friend request: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
