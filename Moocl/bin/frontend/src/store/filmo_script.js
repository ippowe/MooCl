function (input_movie_id) {
var filmo_list = [] ;
db.movie_info.aggregate([
	{$match :{"_id":input_movie_id }},
	{$project : {"person.person_id":1,"person.person_name":1}}, 
	{$unwind : "$person"}
	]).forEach(function(doc) {
			filmo_list.push( db.movie_info.aggregate([{$match :{"person": {$elemMatch: {"person_id" :doc.person.person_id} }}},{$project : {"person":0}}])["_batch"] )
		})
                
return filmo_list
}



