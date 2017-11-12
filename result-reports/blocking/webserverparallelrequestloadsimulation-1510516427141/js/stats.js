var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "239361",
        "ok": "239361",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "1000",
        "ok": "1000",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "4126",
        "ok": "4126",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "2511",
        "ok": "2511",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1068",
        "ok": "1068",
        "ko": "-"
    },
    "percentiles1": {
        "total": "2516",
        "ok": "2518",
        "ko": "-"
    },
    "percentiles2": {
        "total": "3519",
        "ok": "3518",
        "ko": "-"
    },
    "percentiles3": {
        "total": "4009",
        "ok": "4009",
        "ko": "-"
    },
    "percentiles4": {
        "total": "4040",
        "ok": "4040",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 0,
        "percentage": 0
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 42666,
        "percentage": 18
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 196695,
        "percentage": 82
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "221.631",
        "ok": "221.631",
        "ko": "-"
    }
},
contents: {
"req_feed-request-bdb91": {
        type: "REQUEST",
        name: "Feed request",
path: "Feed request",
pathFormatted: "req_feed-request-bdb91",
stats: {
    "name": "Feed request",
    "numberOfRequests": {
        "total": "239361",
        "ok": "239361",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "1000",
        "ok": "1000",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "4126",
        "ok": "4126",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "2511",
        "ok": "2511",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1068",
        "ok": "1068",
        "ko": "-"
    },
    "percentiles1": {
        "total": "2517",
        "ok": "2517",
        "ko": "-"
    },
    "percentiles2": {
        "total": "3518",
        "ok": "3518",
        "ko": "-"
    },
    "percentiles3": {
        "total": "4009",
        "ok": "4009",
        "ko": "-"
    },
    "percentiles4": {
        "total": "4040",
        "ok": "4040",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 0,
        "percentage": 0
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 42666,
        "percentage": 18
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 196695,
        "percentage": 82
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "221.631",
        "ok": "221.631",
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
