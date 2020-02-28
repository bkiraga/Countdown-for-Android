package project.thirdYear.countdown
import android.util.Log
import java.io.File
class LettersSolver {

    class Node (var char:String){

        var children:HashMap<String, Node> = HashMap(26)
        var isWord:Boolean = false

    }

    class Trie{
        var root:Node = Node("")

        public fun add_word(word:String){
            var node = root
            for (char: Char in word){
                if (!node.children.keys.contains(char.toString())){
                    node.children[char.toString()] = Node(char.toString())
                }
                node = node.children[char.toString()]!!
            }
            node.isWord = true
        }

        public fun search_word(word:String): Boolean {
            var node = root
            for (char:Char in word){
                if (!node.children.containsKey(char.toString())){
                    return false
                }
                node = node.children[char.toString()]!!
            }
            return node.isWord
        }

    }

    fun main(args:String){
        val t = LettersSolver.Trie()
        File("../../../../../../../../../words.txt").forEachLine {
            t.add_word(it.toString())
            Log.d("SOLVER","${t.search_word(it.toString())}")
        }
    }
}