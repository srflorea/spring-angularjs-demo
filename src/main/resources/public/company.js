angular.module('demo', [])
.controller('companyCtrl', function($scope, $http) {
   
	$scope.companies = [];
	
	$scope.name = null;
	$scope.address = null;
	$scope.city = null;
	$scope.country = null;
	$scope.email = null;
	$scope.phone = null;
	
	
	$http.get('/companies').
        then(function(response) {
            $scope.companies = response.data;
        });
	
	$scope.addCompany = function() {
		var data = {};
		data["name"] = $scope.name;
		data["address"] = $scope.address;
		data["city"] = $scope.city;
		data["country"] = $scope.country;
		data["email"] = $scope.email;
		data["phone"] = $scope.phone;
		
		
		$http.post("/companies", data)
		   .then(
		       function(response){
		    	   console.log(response.data)
		         $scope.companies.push(response.data)
		       }, 
		       function(response){
		    	   console.log(response.data)
		       }
		    );
	}
});