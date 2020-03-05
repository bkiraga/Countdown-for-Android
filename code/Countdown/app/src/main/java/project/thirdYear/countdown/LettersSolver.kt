package project.thirdYear.countdown
import android.util.Log
import java.io.File
import android.content.Context
import android.nfc.Tag
import android.widget.Toast
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class LettersSolver {

    class Node (var char:String){

        var character:String = ""
        var children:HashMap<String, Node> = HashMap(26)
        var isWord:Boolean = false

        public fun toStr() :String {
            return "char: $character, isWord: $isWord\nchildren: $children"
        }

    }

    class Trie {

        var size = 0
        var root: Node = Node("")

        public fun add_word(word: String) {
            //Log.d(TAG, "ADDING $word")
            var node = root
            for (char: Char in word.trim()) {
                if (!node.children.keys.contains(char.toString())) {
                    node.children[char.toString()] = Node(char.toString())
                    node.character = char.toString()
                }
                //Log.d(TAG, "TRIE is: ${node.toStr()}\n")

                node = node.children[char.toString()]!!
            }
            node.isWord = true
            size += 1
            //Log.d(TAG, "END - TRIE is: ${node.toStr()}\n")
        }

        public fun getTrieSize(): Int {
            return size
        }

        public fun trieToString(): String {
            return root.children.toString()
        }

        public fun search_word(word: String): Boolean {
            Log.d(TAG, "Searching for: $word")
            var node = root
            for (char: Char in word) {
                if (!node.children.containsKey(char.toString())) {
                    return false
                }
                //Log.d(TAG, "Trie is ${node.children}")

                node = node.children[char.toString()]!!
                //Log.d(TAG, node.toStr())
                //Log.d(TAG, "terminal: ${node.isWord}")

            }
            //Log.d(TAG, "Search:$word is present: ${node.isWord}\n")
            return node.isWord
        }

        fun permutation(letters:List<String>):ArrayList<ArrayList<String>> {

            var perms = ArrayList<ArrayList<String>>()

            perms.add(ArrayList<String>())

            for (i in 0 until letters.size)
            {
                var current = ArrayList<ArrayList<String>>()

                for (l in perms)
                {
                    for (j in 0 until l.size + 1)
                    {
                        l.add(j, letters.get(i))
                        var temp = ArrayList<String>(l)
                        current.add(temp)
                        l.removeAt(j)
                    }
                }
                perms = ArrayList<ArrayList<String>>(current)
            }
            return perms
        }

        fun solutionLength(word:String):Pair<Int, String>{
            var mutex = Mutex()
                var node = root
                var length = 0
                var solution = ""
                var counter = 0

                for (char in word){
                    counter += 1

                    if (!node.children.containsKey(char.toString())){
                        Log.d(TAG, "length: $length solution: $solution")
                        return Pair(length, solution)
                    }

                    if (node.isWord){
                        length = counter
                        solution = word.substring(0, counter)
                        Log.d(TAG, "length: $length solution: $solution")
                    }
                    node = node.children[char.toString()]!!
                }
                Log.d(TAG, "length: $length solution: $solution")
                return Pair(length, solution)
            }




    companion object {
        private val TAG = "LettersSolver"
    }
    }
}