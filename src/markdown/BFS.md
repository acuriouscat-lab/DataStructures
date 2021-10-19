
127 成语接龙

单向BFS

```java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)){
            return 0;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> que = new LinkedList<>();
        visited.add(beginWord);
        que.add(beginWord);
        int len = 1;
        while(!que.isEmpty()){
            int size = que.size();
            len++;
            for(int j = 0; j < size; j++){
                String cur = que.poll();
                char[] str = cur.toCharArray();
                for(int i = 0; i < cur.length(); i++){
                    char c = cur.charAt(i);
                    for(char tm = 'a'; tm <= 'z'; tm++){
                        if(c != tm){
                            str[i] = tm;
                            String next = String.valueOf(str);
                            if(set.contains(next) && !visited.contains(next)){
                                if(next.equals(endWord)){
                                    return len;
                                }
                                que.add(next);
                                visited.add(next);
                            }
                            str[i] = c;
                        }
                    }
                }
            }
        }
        return 0;
    }
}
```


双向BFS

```java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)){
            return 0;
        }
        Set<String> visited = new HashSet<>();
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);
        visited.add(beginWord);
        startSet.add(beginWord);
        int len = 1;
        while(!startSet.isEmpty()){
            len++;
            HashSet<String> nextSet = new HashSet<>();
            for(String cur : startSet){
                for(int i = 0; i < cur.length(); i++){
                    char[] str = cur.toCharArray();
                    char c = str[i];
                    for(char tm = 'a'; tm <= 'z'; tm++){
                        if(c != tm){
                            str[i] = tm;
                            String next = String.valueOf(str);
                            if(endSet.contains(next)){
                                return len;
                            }
                            if(set.contains(next) && !visited.contains(next)){
                                nextSet.add(next);
                                visited.add(next);
                            }
                            str[i] = c;
                        }
                    }
                }
            }
            startSet = nextSet.size() < endSet.size() ? nextSet : endSet;
            endSet = startSet == nextSet ? endSet : nextSet;
        }
        return 0;
    }
}
```


403 青蛙过河

DFS

```java
class Solution {
    HashMap<Integer,Integer> map = new HashMap<>();
    //int[][] cache = new int[2009][2009];
    Map<String,Boolean> cache = new HashMap<>();
    public boolean canCross(int[] ss) {
        int n = ss.length;
        for (int i = 0; i < n; i++) {
            map.put(ss[i], i);
        }
        // check first step
        if (!map.containsKey(1)) return false;
        return ifCanCross(ss,1, 1);
    }

    public boolean ifCanCross(int[] stones,int index,int k){
        // if(cache[index][k] != 0){
        //     return cache[index][k] == 1;
        // }
        String key = index + "_" + k;
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        if(index == (stones.length - 1)){
            return true;
        }
        for(int i = -1; i <= 1; i++){
            if(k + i == 0){
                continue;
            }
            int next = stones[index] + k + i;
            if(map.containsKey(next)){
                boolean cur = ifCanCross(stones,map.get(next),k + i);
                // cache[index][k] = cur ? 1 : -1
                cache.put(key,cur);
                if(cur){
                    return true;
                }
            }
        }
        cache.put(key,false);
        // cache[index][k] = -1;
        return false;
    }

}
```


BFS 

```java
class Solution {

    Map<Integer,Integer> map = new HashMap<>();

    public boolean canCross(int[] stones) {
        for(int i = 0; i < stones.length; i++){
            map.put(stones[i],i);
        }
        if(!map.containsKey(1)){
            return false;
        }
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{1,1});
        int n = stones.length;
        boolean[][] visit = new boolean[n][n];
        visit[1][1] = true;
        while(!que.isEmpty()){
            int[] cur = que.poll();
            int idx = cur[0],step = cur[1];
            if(idx == n -1) return true;
            for(int i = -1; i <= 1; i++){
                if(step + i == 0){
                    continue;
                }
                int next = stones[idx] + step + i;
                if(map.containsKey(next)){
                    int nIdx = map.get(next);
                    if(nIdx == n - 1){
                        return true;
                    }
                    if(!visit[nIdx][step + i]){
                        que.add(new int[]{nIdx,step + i});
                        visit[nIdx][step + i] = true;
                    }
                }
            }
        }
        return false;
    }
}
```

773、滑动谜题

---

```java
class Solution {

    int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    // 1.需要解决重复加入问题：加入的是一个数组对象，不能直接用 HashSet<int[]>，
    //                      因为是固定长度，选择将元素按照行优先的顺序转换为字符串
    // 2.需要解决转换一次的下一个状态是什么，通过问题 1 将元素转换为字符串后对每个位置进行编码 求出当前位置附近的坐标 neighbors
    public int slidingPuzzle(int[][] board) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                str.append(board[i][j]);
            }
        }
        String init = str.toString();
        if(init.equals("123450")){
            return 0;
        }
        Queue<String> que = new LinkedList<>();
        Set<String> set = new HashSet<String>();
        que.add(init);
        set.add(init);
        int step = 0;
        while(!que.isEmpty()){
            step++;
            int size = que.size();
            for(int i = 0; i < size; i++){
                String cur = que.poll();
                int idx = cur.indexOf('0');
                for(String next : getNext(cur,idx)){
                    if(!set.contains(next)){
                        if(next.equals("123450")){
                            return step;
                        }
                        que.add(next);
                        set.add(next);
                    }
                }
            }
        }
        return -1;
    }


    public List<String> getNext(String str,int idx){
        List<String> ret = new ArrayList<>();
        char[] strs = str.toCharArray();
        for(int neighbor : neighbors[idx]){
            swap(strs,idx,neighbor);
            ret.add(new String(strs));
            swap(strs,idx,neighbor);
        }
        return ret;
    }

    public void swap(char[] s,int i,int j){
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

}
```

909. 蛇梯棋

---

```java
class Solution {



    public int snakesAndLadders(int[][] board) {
        int N = board.length;
        // 将原始矩阵扁平化
        int[] nums = new int[N * N + 1];
        int idx = 1;
        boolean isRight = true;
        for(int i = N - 1; i >= 0; i--){
            for(int j = (isRight ? 0 : N - 1); isRight ? j < N : j >= 0; j += isRight ? 1 : -1){
                nums[idx++] = board[i][j];
            }
            isRight = !isRight;
        }
        int ans = bfs(nums,N);
        return ans;
    }

    public int bfs(int[] nums,int N){
        Queue<Integer> que = new LinkedList<>();
        que.add(1);
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(1,0);
        while(!que.isEmpty()){
            int cur = que.poll();
            int step = map.get(cur);
            for(int i = 1; i <= 6; i++){
                int next = cur + i;
                if(next > N * N){
                    break;
                }
                if(nums[next] != -1){
                    next = nums[next];
                }
                if(map.containsKey(next)){
                    continue;
                }
                if(next == N * N){
                    return step + 1;
                }
                que.add(next);
                map.put(next,step + 1);
            }
        }
        return -1;
    }

}
```