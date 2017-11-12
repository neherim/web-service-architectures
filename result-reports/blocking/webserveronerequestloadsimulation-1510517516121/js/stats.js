var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "416802",
        "ok": "416802",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "1000",
        "ok": "1000",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2130",
        "ok": "2130",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1441",
        "ok": "1441",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "390",
        "ok": "390",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1396",
        "ok": "1395",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1828",
        "ok": "1828",
        "ko": "-"
    },
    "percentiles3": {
        "total": "2007",
        "ok": "2007",
        "ko": "-"
    },
    "percentiles4": {
        "total": "2038",
        "ok": "2038",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 0,
        "percentage": 0
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 167546,
        "percentage": 40
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 249256,
        "percentage": 60
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "385.571",
        "ok": "385.571",
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
        "total": "416802",
        "ok": "416802",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "1000",
        "ok": "1000",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2130",
        "ok": "2130",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1441",
        "ok": "1441",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "390",
        "ok": "390",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1396",
        "ok": "1396",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1828",
        "ok": "1828",
        "ko": "-"
    },
    "percentiles3": {
        "total": "2007",
        "ok": "2007",
        "ko": "-"
    },
    "percentiles4": {
        "total": "2038",
        "ok": "2038",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 0,
        "percentage": 0
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 167546,
        "percentage": 40
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 249256,
        "percentage": 60
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "385.571",
        "ok": "385.571",
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
