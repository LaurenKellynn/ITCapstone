
<?php

 //* Programmer Name: Lauren Kellynn
 //* Date: 4/12/2023
 //* IT Capstone Project: PHP for Registration
 

session_start();

if (!isset($_SESSION['cart'])) {
    echo "Your cart is empty!";
    exit;
}

$total_amount = calculate_total_amount($_SESSION['cart']);
$order_id = $_SESSION['order_id'];

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $con = new mysqli("itcapstonegroup3.mysql.database.azure.com", "ITCapstone3", "", "db_group3", 3306);
    if ($con->connect_error) {
        die('Connection Failed : '.$con->connect_error);
    } else {
        //Updates the total for the order in the Orders table on the database
        $stmt_order = $con->prepare("UPDATE orders SET total_amount = ? WHERE order_id = ?");
        $stmt_order->bind_param("di", $total_amount, $order_id);
        $stmt_order->execute();
        $stmt_order->close();

        //Sets the order from the default of "pending" to "confirmed" in the database
        $stmt_status = $con->prepare("UPDATE orders SET status = 'confirmed' WHERE order_id = ?");
        $stmt_status->bind_param("i", $order_id);
        $stmt_status->execute();
        $stmt_status->close();
    } 
        //To print the confirmation page once the checkout button is clicked
        echo '<link rel="stylesheet" href="confirmation.css">';

        echo '<div class="confirmation" id="popup">';
        echo '<ion-icon name="bag-check-outline" class="bag"></ion-icon>';
        echo '<h2>Thank You!</h2>';
        echo '<div name="order_id" >Your order has been placed! Order Number: ' . $order_id . '</div>';
        echo '<button type="button" class="ok">OK</button>';
        echo '</div>';

        //Redirects back to the homepage after the ok button is clicked on the confirmation window
        echo '<script>
            document.querySelector(".ok").addEventListener("click", function() {
            window.location.href = "http://itcapstonegroupthree.infinityfreeapp.com/HomePage.html?i=1";
            });
            </script>';
}

//Calculate total for the cart
function calculate_total_amount($cart) {
    $total_amount = 0;

    foreach ($cart as $item) {
        $total_amount += $item['price'] * $item['quantity'];
    }

    return $total_amount;
}

?>
