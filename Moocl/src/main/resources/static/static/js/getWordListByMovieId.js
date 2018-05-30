function (input_movie_id) {
var word_list = [] ;

db.test_review.aggregate([{$match : {"_id.movie_ref.$id" : input_movie_id }},{$project : {"_id.movie_ref" : 1, "gurumi_word" : 1}},    
        {$unwind: "$gurumi_word" },    
        {$group: {_id: { term: "$gurumi_word"}, count: {$sum : 1}}}, 
        { $sort : { count : -1 } },{$limit : 50}    ],
        { allowDiskUse: true} ).forEach(function(doc) {
            print(JSON.stringify(doc))
            pushData = {"term" : doc._id.term , "count" : doc.count};
            word_list.push(pushData);
})
                
return word_list
}