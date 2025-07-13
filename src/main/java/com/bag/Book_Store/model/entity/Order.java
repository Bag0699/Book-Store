package com.bag.Book_Store.model.entity;

import com.bag.Book_Store.model.dto.response.OrderWithUserDetailsResponse;
import com.bag.Book_Store.util.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
//@NamedStoredProcedureQuery(
//        name = "Order.getOrdersByMonth",
//        procedureName = "GetOrdersByMonth",
//        parameters = {
//                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_year", type = Integer.class),
//                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_month", type = Integer.class)
//        },
//        resultClasses = OrderWithUserDetailsResponse.class
//)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "GetOrdersByMonthMapping",
                query = "CALL GetOrdersByMonth(:p_year, :p_month)",
                resultSetMapping = "OrderWithUserDetailsResponseMapping"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "OrderWithUserDetailsResponseMapping",
                classes = @ConstructorResult(
                        targetClass = OrderWithUserDetailsResponse.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "order_date", type = LocalDate.class),
                                @ColumnResult(name = "total_amount", type = BigDecimal.class),
                                @ColumnResult(name = "status", type = String.class),
                                @ColumnResult(name = "user_id", type = Long.class),
                                @ColumnResult(name = "username", type = String.class),
                                @ColumnResult(name = "full_name", type = String.class)
                        }
                )
        )
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
}
