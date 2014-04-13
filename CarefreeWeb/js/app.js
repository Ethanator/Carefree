(function (window) {


	//////////////////////////////////////////////
	// EmberData
	//////////////////////////////////////////////

	App.ApplicationAdapter = DS.FirebaseAdapter.extend(
		firebase: new Firebase('http://carefree.firebaseIO.com');
	);


})(window);