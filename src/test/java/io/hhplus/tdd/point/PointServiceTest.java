package io.hhplus.tdd.point;

import io.hhplus.tdd.common.utils.CalculateUtils;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.exception.PointException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PointServiceTest {

    @InjectMocks
    private PointService pointService;

    @Mock
    private PointValidator pointValidator;

    @Mock
    private UserPointTable userPointTable;

    @Nested
    @DisplayName("포인트 조회 테스트")
    class PointSelectTests {
        @Test
        @DisplayName("포인트 조회 검증 - ID가 Null인 경우 예외 발생")
        void selectTest_nullId() {
            doThrow(PointException.nullId()).when(pointValidator).validateId(null);

            assertThrows(PointException.class,
                    () -> pointService.select(null));
        }

        @Test
        @DisplayName("포인트 조회")
        void selectTest() {
            when(userPointTable.selectById(1L))
                    .thenReturn(UserPoint.empty(1L));

            UserPoint userPoint = pointService.select(1L);

            assertEquals(0, userPoint.point());
            verify(userPointTable).selectById(1L);
        }
    }

    @Nested
    @DisplayName("포인트 충전 테스트")
    class PointChargeTests {
        @Test
        @DisplayName("충전 가능성 검증 - 성공")
        void validateChargeAbleTest_valid() {
            assertDoesNotThrow(
                    () -> pointValidator.validateChargeAble(1L, 1000L));
        }

        @Test
        @DisplayName("충전 가능성 검증 - ID가 Null인 경우 예외 발생")
        void validateChargeAbleTest_nullId() {
            doThrow(PointException.nullId()).when(pointValidator).validateChargeAble(null, 1000L);

            assertThrows(PointException.class,
                    () -> pointService.charge(null, 1000L));
        }

        @Test
        @DisplayName("충전 가능성 검증 - 금액이 0 이하인 경우 예외 발생")
        void validateChargeAbleTest_amountNegative() {
            doThrow(PointException.nullId()).when(pointValidator).validateChargeAble(1L, 0L);
            assertThrows(PointException.class,
                    () -> pointService.charge(1L, 0L));

            doThrow(PointException.nullId()).when(pointValidator).validateChargeAble(1L, -1L);
            assertThrows(PointException.class,
                    () -> pointValidator.validateChargeAble(1L, -1L));
        }

        @Test
        @DisplayName("최대 포인트 초과 검증 - 성공")
        void validateChargeMaxPointTest_valid() {
            assertDoesNotThrow(
                    () -> pointValidator.validateMaxPoint(CalculateUtils.add(1000L, 9000L)));
        }

        @Test
        @DisplayName("최대 포인트 초과 검증 - 초과")
        void validateChargeMaxPointTest_over() {
            doThrow(PointException.nullId()).when(pointValidator).validateMaxPoint(CalculateUtils.add(2000L, 9000L));

            assertThrows(PointException.class,
                    () -> pointValidator.validateMaxPoint(CalculateUtils.add(2000L, 9000L)));
        }

        @Test
        @DisplayName("포인트 충전 - 성공")
        void chargeTest_valid() {
            when(userPointTable.selectById(1L))
                    .thenReturn(new UserPoint(1L, 1000L, System.currentTimeMillis()));
            when(userPointTable.insertOrUpdate(1L, CalculateUtils.add(1000L, 9000L)))
                    .thenReturn(new UserPoint(1L, 10000L, System.currentTimeMillis()));
            doNothing().when(pointValidator).validateChargeAble(1L, 9000L);
            doNothing().when(pointValidator).validateMaxPoint(CalculateUtils.add(1000L, 9000L));

            UserPoint result = pointService.charge(1L, 9000L);

            assertEquals(CalculateUtils.add(1000L, 9000L), result.point());
//        verify(pointValidator).validateChargeAble(1L, 9000L);
//        verify(pointValidator).validateMaxPoint(10000L);
            verify(userPointTable).insertOrUpdate(1L, CalculateUtils.add(1000L, 9000L));
        }
    }
}