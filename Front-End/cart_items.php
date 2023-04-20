/*
 * Programmer Name: Emma Rawstron & Lauren Kellynn
 * Date: 4/10/2023
 * IT Capstone Project: Add to cart
 */
<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

session_start();

$product_id = isset($_POST['product_id']) ? intval($_POST['product_id']) : 0;
$size = isset($_POST['size']) ? $_POST['size'] : '';
$user_id = isset($_SESSION['user_id']) ? intval($_SESSION['user_id']) : 0;

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    echo "product_id: $product_id<br>";
    echo "size: $size<br>";
    echo "user_id: $user_id<br>";
    $con = new mysqli("itcapstonegroup3.mysql.database.azure.com", "ITCapstone3", "", "db_group3", 3306);
    if($con->connect_error){
        die('Connection Failed : '.$con->connect_error);
    } else {
        // Check stock and price for the product
        $stmt = $con->prepare("SELECT name, stock, price FROM products WHERE product_id = ?");
        $stmt->bind_param("i", $product_id);
        $stmt->execute();
        $result = $stmt->get_result();
        $product = $result->fetch_assoc();

        $price = $product['price'];
        $name = $product['name'];
        $image_url = "images/{$product_id}.jpg";

        if ($product['stock'] > 0) {
            // Create an order if not already created
            if (!isset($_SESSION['order_id'])) {
                if ($user_id > 0) {
                    $stmt_order = $con->prepare("INSERT INTO orders (user_id) VALUES (?)");
                    $stmt_order->bind_param("i", $user_id);
                } else {
                    $stmt_order = $con->prepare("INSERT INTO orders (user_id) VALUES (NULL)");
                }
                $stmt_order->execute();
                $order_id = $stmt_order->insert_id;
                $_SESSION['order_id'] = $order_id;
                $stmt_order->close();
            } else {
                $order_id = $_SESSION['order_id'];
            }

            // Add the product to the cart and associate with the order
            $stmt2 = $con->prepare("INSERT INTO order_items(order_id, price, product_id, size) values(?, ?, ?, ?)");
            $stmt2->bind_param("idds", $order_id, $price, $product_id, $size);
            $stmt2->execute();
            $stmt2->close();

            // Update the stock
            $new_stock = $product['stock'] - 1;
            $stmt3 = $con->prepare("UPDATE products SET stock = ? WHERE product_id = ?");
            $stmt3->bind_param("ii", $new_stock, $product_id);
            $stmt3->execute();
            $stmt3->close();

            // Store the cart in the session if it's not there yet
            if (!isset($_SESSION['cart'])) {
                $_SESSION['cart'] = array();
            }

            // Add the product to the cart
            $cart_item = array(
                "product_id" => $product_id,
                "size" => $size,
                "price" => $price,
                "quantity" => 1,
                "name" => $name,
                "image_url" => $image_url
            );
            
            $item_exists_in_cart = false;

            foreach ($_SESSION['cart'] as &$existing_cart_item) {
                if ($existing_cart_item['product_id'] == $product_id && $existing_cart_item['size'] == $size) {
                    // If the item is already in the cart, increment its quantity
                    $existing_cart_item['quantity'] += 1;
                    $item_exists_in_cart = true;
                    break;
                }
            }

            // If the item is not in the cart, add it
            if (!$item_exists_in_cart) {
                array_push($_SESSION['cart'], $cart_item);
            }

        } else {
            echo "Product is out of stock.";
        }

        $stmt->close();
        $con->close();
    }
    $previous_page = $_SERVER['HTTP_REFERER'];
    $previous_page .= (parse_url($previous_page, PHP_URL_QUERY) ? '&' : '?') . 'cart_updated=1';
    header("Location: Shop.php?cart_updated=1");
    exit;
} else {
    echo "Please provide valid input values.";
}
?>
