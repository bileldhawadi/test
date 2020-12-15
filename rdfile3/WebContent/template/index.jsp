<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html lang="en">

<head>
  
  <meta charset="utf-8">
  <link rel="stylesheet" type="text/css" href="template/mylib/bootstrap.min.css">
  <script src="template/mylib/bootstrap.min.js"></script>
  <script src="template/mylib/jquery.min.js"></script>
  <title>Index</title>
  <style type="text/css">
  	body{
  		background-color:#a8d3ff;
  	}
  	table th{
  		text-align:center;
  	}
  	table tbody td:nth-child(even){
  		background-color:#94c9ff;
  	}
  	table tbody tr:nth-child(even){
  		background-color:#a8d3ff;
  	}
  	
  </style>
</head>

<body id="page-top">

<header style="text-align:center; background-color:#f0f0f0;padding-bottom: 10px; font-family:Arial;position:relative;">
	<div style="position:absolute;">
		<img src="template/mylib/logo.png" style="width:350px;height:300px"/>
	</div>
	<div style="padding-top: 50px;">
	<h2>Direction Centrale Des Systèmes d'Informations</h2>
	<h3>Direction SI Commercial</h3>
	<h4>Division SI Services</h4>
	<h5><em>Application De Gestion Des Statistiques De La Recharge Electronique (TELEPIN)</em></h5>
	</div>
</header>

<center>
<div class="container" style="padding:30px; background-color:#f0f0f0;     margin-bottom:270px;margin-top:20px;">
<form class="md-form" action="" method="post" enctype="multipart/form-data" style="text-align:center">
  <div class="form-group" style="display:inline;">
    <input name="file" id="fileUpload" accept=".csv" type="file" style="display:inline;">
  </div>
  <button type="submit" class="btn btn-primary">Show data</button>
</form>


  <!-- Page Wrapper -->
        <!-- /.container-fluid -->
		<div>${ erreur }</div>
		<c:if test="${total != 0 }">
		<table class="table table-hover data"  style="margin:30px;width:50%;">
			<tbody>
				<tr>
					<td style="background-color:#c9f0ff;"><b>Date De Rapport Global</b></td>
					<td style="background-color:#00d0ff;">${date}</td>
				</tr>
				<tr>
					<td style="background-color:#00d0ff;"><b>Nombre Journalier Total</b></td>
					<td style="background-color:#c9f0ff;">${total}</td>
				</tr>
			</tbody>
		</table>
		<table class="table table-hover data"  style="margin-right:30px; color:#62647e;">
  <thead style=" background-color:#00d0ff;">
    <tr>
      <th scope="col" rowspan="3" style="vertical-align: middle;">#</th>
      <th scope="col" colspan="4">Via Mobile</th>
      <th scope="col" colspan="2">Via DAB</th>
    </tr>
    <tr>
      <th scope="col" colspan="2">Recharge Electronique</th>
      <th scope="col" colspan="2">Paiment Facture</th>
      <th scope="col" colspan="2">Recharge Electronique</th>
    </tr>
    <tr>
      <th scope="col">Nombre</th>
      <th scope="col">Montant(DT)</th>
      <th scope="col">Nombre</th>
      <th scope="col">Montant(DT)</th>
      <th scope="col">Nombre</th>
      <th scope="col">Montant(DT)</th>
    </tr>
  </thead>
  <tbody>
  	
    <c:forEach items="${file}" var="ligne" varStatus="loop">
    	<tr>
	  		<td>${ligne.getName()}</td>
	  		<td>${ligne.getRmNombre()}</td>
	  		<td>${ligne.getRmMontant()}</td>
	  		<td>${ligne.getPfNombre()}</td>
	  		<td>${ligne.getPfMontant()}</td>
	  		<td>${ligne.getrDABNombre()}</td>
	  		<td>${ligne.getrDABMontant()}</td>
  		</tr>
    </c:forEach>
   </tbody>
  
</table>
</c:if>
</div></center>
  <!-- Logout Modal-->
  <footer style="text-align:center; background-color:#f0f0f0; padding:60px;"><h4><u>Copyright &copy; 2020,Tunisie Telecom</u></h4></footer>
</body>
</html>
