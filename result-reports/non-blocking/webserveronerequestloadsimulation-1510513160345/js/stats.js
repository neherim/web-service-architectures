var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "573386",
        "ok": "573386",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "1000",
        "ok": "1000",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1650",
        "ok": "1650",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1047",
        "ok": "1047",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "56",
        "ok": "56",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1026",
        "ok": "1026",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1068",
        "ok": "1068",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1157",
        "ok": "1157",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1226",
        "ok": "1227",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 0,
        "percentage": 0
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 563443,
        "percentage": 98
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 9943,
        "percentage": 2
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "530.422",
        "ok": "530.422",
        "ko": "-"
    }
},
contents: {
"req_tweet-request-cd386": {
        type: "REQUEST",
        name: "Tweet request",
path: "Tweet request",
pathFormatted: "req_tweet-request-cd386",
stats: {
    "name": "Tweet request",
    "numberOfRequests": {
        "total": "573386",
        "ok": "573386",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "1000",
        "ok": "1000",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1650",
        "ok": "1650",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1047",
        "ok": "1047",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "56",
        "ok": "56",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1026",
        "ok": "1026",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1068",
        "ok": "1068",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1157",
        "ok": "1157",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1227",
        "ok": "1227",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 0,
        "percentage": 0
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 563443,
        "percentage": 98
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 9943,
        "percentage": 2
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "530.422",
        "ok": "530.422",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
