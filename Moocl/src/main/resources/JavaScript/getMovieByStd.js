function (movieId,clickWord,condition,grade){
var reviewer_list = [];
var movie_list = [];
db.test_review.aggregate([
    {$project :{"user_id":1,"gurumi_word": 1}},
    {$match : {"_id.movie_ref.$id" : movieId}}, 
    {$match : {"gurumi_word" : {$in : [clickWord]}}}, 
    {$project : {"_id" : 0 ,"user_id" : 1}},{$limit : 7}]).forEach(function (doc) {
        reviewer_list.push(doc.user_id)
        } );if(condition == "gte") {
	db.test_review.aggregate([ {$project : {"user_grade" : 1}},	
	    {$match : {"user_grade" : {$gte : grade}}}, 	
	    {$match : {"_id.user_id" : {$in :reviewer_list}}}, 	
	    {$group : {_id :"$_id.movie_ref", 	
	               count:{$sum :1 }}},	
	    {$sort : {"count" : -1}}	
	    	
	    ]).forEach(function(doc) {
	            pushData = {"movie" : doc._id, "count" : doc.count};
	            movie_list.push(pushData);
	});
}else if(condition == "lte") {
	db.test_review.aggregate([ {$project : {"user_grade" : 1}},
		
	    {$match : {"user_grade" : {$lte : grade}}}, 
	
	    {$match : {"_id.user_id" : {$in :reviewer_list}}}, 
	
	    {$group : {_id :"$_id.movie_ref", 
	
	               count:{$sum :1 }}},
	
	    {$sort : {"count" : -1}}
	
	    
	
	    ]).forEach(function(doc) {
	            pushData = {"movie" : doc._id, "count" : doc.count};
	            movie_list.push(pushData);
	});	
}
return movie_list
}





