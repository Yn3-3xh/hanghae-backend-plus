package io.hhplus.tdd.point;

import io.hhplus.tdd.common.utils.CalculateUtils;
import io.hhplus.tdd.database.UserPointTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

    private final UserPointTable userPointTable;
    private final PointValidator pointValidator;

    // 포인트 조회
    public UserPoint select(Long id) {
        pointValidator.validateId(id);

        return userPointTable.selectById(id);
    }

    // 포인트 '충전/사용' 내역 조회

    // 포인트 충전
    public UserPoint charge(Long id, Long amount) {
        pointValidator.validateChargeAble(id, amount);

        UserPoint userPoint = userPointTable.selectById(id);
        Long pointSum = CalculateUtils.add(userPoint.point(), amount);
        pointValidator.validateMaxPoint(pointSum);

        return userPointTable.insertOrUpdate(id, pointSum);
    }

    // 포인트 사용

}
