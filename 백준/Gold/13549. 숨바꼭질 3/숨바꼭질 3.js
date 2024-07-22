const fs = require("fs");
const input = fs
    .readFileSync("/dev/stdin")
    .toString()
    .trim()
    .split(" ")
    .map(Number);

let time = Array(100005).fill(100000);
let dist = Array(100005).fill(0);

function bfs(start, goal) {
    let needVisit = [];

    needVisit.push(start);
    dist[start] = 0;
    time[start] = 0;

    while (needVisit.length != 0) {
        const node = needVisit.shift();
        let new_time = 0;
        if (start == goal) return 0;

        if (dist[node - 1] == 0 && node - 1 >= 0) {
            needVisit.push(node - 1);
            dist[node - 1] = dist[node] + 1;
            new_time = time[node] + 1;
            if (new_time < time[node - 1]) time[node - 1] = new_time;
        } else if (dist[node - 1] == dist[node] + 1) {
            new_time = time[node] + 1;
            if (new_time < time[node - 1]) time[node - 1] = new_time;
        }
        if (dist[node + 1] == 0 && node + 1 <= 100000) {
            needVisit.push(node + 1);
            dist[node + 1] = dist[node] + 1;
            new_time = time[node] + 1;
            if (new_time < time[node + 1]) time[node + 1] = new_time;
        } else if (dist[node + 1] == dist[node] + 1) {
            new_time = time[node] + 1;
            if (new_time < time[node + 1]) time[node + 1] = new_time;
        }

        if (dist[node * 2] == 0 && node * 2 <= 100000) {
            needVisit.push(node * 2);
            dist[node * 2] = dist[node] + 1;
            new_time = time[node];
            if (new_time < time[node * 2]) time[node * 2] = new_time;
        } else if (dist[node * 2] == dist[node] + 1) {
            new_time = time[node];
            if (new_time < time[node * 2]) time[node * 2] = new_time;
        }
    }

    return time[goal];
}

let answer = bfs(input[0], input[1]);
console.log(answer);