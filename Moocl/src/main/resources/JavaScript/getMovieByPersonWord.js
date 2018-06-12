function (personId,clickWord,condition,grade) {
var actorMovie= [];
var reviewer_list = [];
var movie_list = [];    
db.movie_info.aggregate([	{$match : {"person.person_id" : NumberInt(personId)}}, 
    	{$project :{"_id": 1}}		]).forEach(function (doc) {			actorMovie.push(doc._id)				});
db.test_review.aggregate([	{$match : {'_id.movie_ref.$id' : {$in : actorMovie}}},
		{$match : {"gurumi_word" : {$in : [clickWord]}}},
		{$project : {"_id" : 0,"user_id":1}}		]).forEach(function (doc) {				reviewer_list.push(doc.user_id)			})if(condition == "gte") {	
	db.test_review.aggregate([ {$project : {"user_grade" : 1}},	
	    {$match : {"_id.user_id" : {$in :reviewer_list}}}, 	
	    {$match : {"user_grade" : {$gte : grade}}}, 	
	    {$group : {_id :"$_id.movie_ref", 	
	               count:{$sum :1 }}},	
	    {$sort : {"count" : -1}},	
	    {$limit : 11}	
	    ]).forEach(function(doc) {	    	pushData = {"movie" : doc._id.$id, "count" : doc.count};	        movie_list.push(pushData);		});	} else if(condition == "lte"){	db.test_review.aggregate([ {$project : {"user_grade" : 1}},			    {$match : {"_id.user_id" : {$in :reviewer_list}}}, 		    {$match : {"user_grade" : {$lte : grade}}}, 		    {$group : {_id :"$_id.movie_ref", 		               count:{$sum :1 }}},		    {$sort : {"count" : -1}},		    {$limit : 11}		    ]).forEach(function(doc) {	    	pushData = {"movie" : doc._id.$id, "count" : doc.count};	        movie_list.push(pushData);		});		}

return movie_list
}