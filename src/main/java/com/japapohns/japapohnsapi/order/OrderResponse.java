package com.japapohns.japapohnsapi.order;

import java.math.BigDecimal;

public record OrderResponse(long menuItemId, long quantity, BigDecimal total) {
}
