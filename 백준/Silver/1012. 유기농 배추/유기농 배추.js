const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');;

let T = input.shift()-'';
let TC = input;
let answer = [];

function bfs(row, col) {
    let point = [[row, col]];
    let move = [[0, 1], [0, -1], [1, 0], [-1, 0]];

    while(point.length) {
        let [x, y] = point.shift();

        if (land[x][y]) land[x][y] = 0;
        else continue;

        for (let idx = 0; idx < 4; idx++) {
            let moveX = x + move[idx][0];
            let moveY = y + move[idx][1];

            if (moveX < 0 || moveX >= N || moveY < 0 || moveY >= M) continue;
            else point.push([moveX, moveY]);
        }

    }
}

while (T) {
    var [M, N, K] = TC[0].split(" ").map(Number);
    var land = Array.from(Array(N), () => new Array(M).fill(0));
    let vegetable = TC.slice(1, K+1);
    let worm = 0;

    for (let j = 0; j < vegetable.length; j++) {
        let col = vegetable[j].split(" ")[0]-'';
        let row = vegetable[j].split(" ")[1]-'';

        land[row][col] = 1;
    }
    
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < M; j++) {
            if (land[i][j] == 1)   {
                bfs(i, j);
                worm++;
            }
        }
    }

    answer.push(worm);
    TC = TC.slice(K+1);
    T--;
}
console.log(answer.join('\n'));