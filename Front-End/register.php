
<?php
// * Programmer Name: Lauren Kellynn
// * Date: 4/12/2023
// * IT Capstone Project: PHP for Registration

header('Location: ' . $_SERVER['HTTP_REFERER']);
	
	$username = $_POST['username'];
	$password = $_POST['password'];
	$email = $_POST['email'];
	
	if ($_SERVER['REQUEST_METHOD'] == 'POST') {
		$con = new mysqli("itcapstonegroup3.mysql.database.azure.com", "ITCapstone3", "Group3isthebest!", "db_group3", 3306);
		if($con->connect_error){
			die('Connection Failed : '.$con->connect_error);
		}else{
			$stmt = $con->prepare("INSERT INTO user_login(username, password, email) values(?, ?, ?)");
			$stmt->bind_param("sss", $username, $password, $email);
			$stmt->execute();
			$stmt->close();
			$con->close();
		}
	}
	
?>