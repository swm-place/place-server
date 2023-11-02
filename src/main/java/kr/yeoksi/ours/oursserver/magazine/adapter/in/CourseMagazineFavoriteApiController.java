package kr.yeoksi.ours.oursserver.magazine.adapter.in;

import kr.yeoksi.ours.oursserver.magazine.exception.NoPermissionOfFavoriteException;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class CourseMagazineFavoriteApiController {

    private final CourseMagazineFavoriteService courseMagazineFavoriteService;


    @PostMapping("/favorites/{userId}/magazines/{courseMagazineId}")
    public void addFavorite(@RequestHeader("X-User-Uid") String requestedUserId,
                            @PathVariable String userId,
                            @PathVariable Long courseMagazineId) {
        if (!requestedUserId.equals(userId))
            throw new NoPermissionOfFavoriteException();

        courseMagazineFavoriteService.addFavorite(userId, courseMagazineId);
    }
}
