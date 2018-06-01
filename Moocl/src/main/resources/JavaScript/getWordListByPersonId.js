function (input_personId) {
	var word_list= [];
	var actorMovie= [];

	db.movie_info.aggregate([{$match : {"person.person_id" : input_personId}}, {$project :{"_id": 1}}
	]).forEach(function (doc) {
        actorMovie.push(doc._id)
});      

db.test_review.aggregate([{$match : {'_id.movie_ref.$id' : {$in : actorMovie}}},
    {$project : {"_id" : 0, "gurumi_word" : 1}},
    {$unwind: "$gurumi_word" },  
    {$group: {_id: { term: "$gurumi_word"}, count: {$sum : 1}}},
    { $sort : { count : -1 } },{$limit : 100} ],{ allowDiskUse: true} ).forEach(function(doc) {
        print(JSON.stringify(doc))
        pushData = {"term" : doc._id.term , "count" : doc.count};
        word_list.push(pushData);
});

return word_list;
};