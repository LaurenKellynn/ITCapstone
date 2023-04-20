/*
 * Programmer Name: Lauren Kellynn & Emma Rawstron
 * Date: 4/12/2023
 * IT Capstone Project: PHP for Login
 */

<?php
session_start();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $username = $_POST['username'];
    $password = $_POST['password'];

    $con = new mysqli("itcapstonegroup3.mysql.database.azure.com", "ITCapstone3", "", "db_group3", 3306);
    if ($con->connect_error) {
        die('Connection Failed : ' . $con->connect_error);
    } else {
        $stmt = $con->prepare("SELECT user_id, password FROM user_login WHERE username = ?");
        $stmt->bind_param("s", $username);
        $stmt->execute();
        $result = $stmt->get_result();

        // Check if the user exists and the password is correct
        if ($result->num_rows > 0) {
            $user = $result->fetch_assoc();
            if ($password === $user['password']) {
                $_SESSION['user_id'] = $user['user_id'];
                $_SESSION['username'] = $username;
                $_SESSION['success_message'] = 'Welcome, ' . $username . '!';
                header('Location: HomePage2.php');
                exit;
            } else {
                $error = "Incorrect password.";
            }
        } else {
            $error = "User does not exist.";
        }

        $stmt->close();
        $con->close();
    }
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Error</title>
</head>
<body>
    <h1>Login Error</h1>
    <?php if (isset($error)) { echo "<p>$error</p>"; } ?>
    <p><a href="LoginForm.html">Back to Login Form</a></p>
</body>
</html>





