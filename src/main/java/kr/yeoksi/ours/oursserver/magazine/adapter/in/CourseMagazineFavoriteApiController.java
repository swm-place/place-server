package kr.yeoksi.ours.oursserver.magazine.adapter.in;

import kr.yeoksi.ours.oursserver.magazine.adapter.in.response.FavoriteCourseMagazineResponse;
import kr.yeoksi.ours.oursserver.magazine.exception.NoPermissionOfFavoriteException;
import kr.yeoksi.ours.oursserver.magazine.service.port.in.CourseMagazineFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CourseMagazineFavoriteApiController {

    private final CourseMagazineFavoriteService courseMagazineFavoriteService;


    @PostMapping("/favorites/{userId}/magazines/{courseMagazineId}")
    public void addFavorite(@RequestHeader("X-User-Uid") String requestedUserId,
                            @PathVariable String userId,
                            @PathVariable Long courseMagazineId) {
        // TODO: 권한 검증 로직 분리
        if (!requestedUserId.equals(userId))
            throw new NoPermissionOfFavoriteException();

        courseMagazineFavoriteService.addFavorite(userId, courseMagazineId);
    }

    @DeleteMapping("/favorites/{userId}/magazines/{courseMagazineId}")
    public void deleteFavorite(@RequestHeader("X-User-Uid") String requestedUserId,
                               @PathVariable String userId,
                               @PathVariable Long courseMagazineId) {
        if (!requestedUserId.equals(userId))
            throw new NoPermissionOfFavoriteException();

        courseMagazineFavoriteService.deleteFavorite(userId, courseMagazineId);
    }

    @GetMapping("/favorites/{userId}/magazines")
    public ResponseEntity<List<FavoriteCourseMagazineResponse>> getFavoriteMagazines(@RequestHeader("X-User-Uid") String requestedUserId,
                                                                                     @PathVariable String userId,
                                                                                     @RequestParam(defaultValue = "0") int page,
                                                                                     @RequestParam(defaultValue = "10") int size) {
        if (!requestedUserId.equals(userId))
            throw new NoPermissionOfFavoriteException();

        return ResponseEntity.ok(courseMagazineFavoriteService.getFavoriteMagazines(userId, page, size)
                .stream()
                .map(FavoriteCourseMagazineResponse::from)
                .toList());
    }
}
