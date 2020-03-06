package project.thirdYear.countdown

import org.junit.Assert.*
import org.junit.Test
import project.thirdYear.countdown.LettersSolver.Node
import project.thirdYear.countdown.LettersSolver.Trie


class LettersSolverTest{

    @Test
    fun testNodeCharacterAttribute(){
        val node = Node("t")
        assertEquals(node.character, "t")
        assertNotEquals(node.character, "") 
        assertNotEquals(node.character, null)
    }

    @Test
    fun testNodeChildrenAttribute(){
        val node = Node("S")
        assertNotEquals(node.children, null)
        assertEquals(node.children, HashMap<String, Node>(26))
        assertEquals(node.children.size, 0)
    }

    @Test
    fun testNodeIsWordAttribute(){
        val node = Node("t")
        assertEquals(node.isWord, false)
        assertNotEquals(node.isWord, true)
        assertNotEquals(node.isWord, "c")
    }

    @Test
    fun testAdditionToTrie(){
        val words = arrayListOf<String>("BALALAIKA", "", "2", "222222222", "j+4i", "LONGERTHANNINELETTERS", "otherCase")
        var trie = Trie()
        for (word in words){
            trie.add_word(word)
        }
        assertNotEquals(trie.size, 7)
        assertEquals(trie.size, 1)
        assertEquals(trie.search_word(words[0]), true)
    }

    @Test
    fun testSearchInTrie(){
        var trie = Trie()
        var results = arrayListOf<Boolean>()
        val words = arrayListOf<String>("BALALAIKA", "", "2", "222222222", "j+4i", "LONGERTHANNINELETTERS", "otherCase")
        for (word in words){
            results.add(trie.search_word(word))

        }
        assertNotEquals(results, arrayListOf(true,true,true,true,true,true,true))
        assertEquals(results, arrayListOf(true))
    }

    @Test
    fun testPermutations(){
        var trie = Trie()
        //var perms = trie.permutation(List<String>())
        //assertEquals()
    }

}