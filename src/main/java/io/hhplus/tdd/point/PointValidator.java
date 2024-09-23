package io.hhplus.tdd.point;

import io.hhplus.tdd.point.exception.PointException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PointValidator {

    public void validateChargeAble(Long id, Long amount) {
        validateId(id);
        validateAmount(amount);
    }

    public void validateId(Long id) {
        if (Objects.isNull(id)) {
            throw PointException.nullId();
        }
    }

    public void validateAmount(Long amount) {
        if (amount <= 0) {
            throw PointException.amountMustBeGreaterThanZero();
        }
    }

    public void validateMaxPoint(Long pointSum) {
        if (pointSum > PointPolicy.MAX_POINT.getPoint()) {
            throw PointException.exceedMaxPoint();
        }
    }
}
