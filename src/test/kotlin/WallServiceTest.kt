import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addNewPosts() {
        val post1 = Post(
            id = 0,
            date = 13062026,
            text = "First post",
            likes = Likes (0, false, true, true),
            postType = "post"
        )
        val post2 = Post(
            id = 0,
            date = 13062026,
            text = "Second post",
            likes = Likes (0, false, true, true),
            postType = "post"
        )

        val result1 = WallService.add(post1)
        val result2 = WallService.add(post2)

        assertEquals(1, result1.id)
        assertEquals(2, result2.id)
    }

    @Test
    fun updateAcceptable() {
        val post = Post(
            id = 0,
            date = 13062026,
            text = "First post",
            likes = Likes (0, false, true, true),
            postType = "post"
        )

        WallService.add(post)
        val result = WallService.update(1)

        assertEquals(true, result)
    }

    @Test
    fun updateUnacceptable() {
        val post = Post(
            id = 0,
            date = 13062026,
            text = "First post",
            likes = Likes (0, false, true, true),
            postType = "post"
        )

        WallService.add(post)
        val result = WallService.update(3)

        assertEquals(false, result)
    }

    @Test
    fun commentSuccessful() {
        val post1 = Post(
            id = 0,
            date = 13062026,
            text = "First post",
            likes = Likes (0, false, true, true),
            postType = "post"
        )
        val post2 = Post(
            id = 0,
            date = 13062026,
            text = "Second post",
            likes = Likes (0, false, true, true),
            postType = "post"
        )

        WallService.add(post1)
        WallService.add(post2)
        val result = WallService.createComment(1, comment = Comment(0, 0,
            23062026, "Comment to first post"))

        assertEquals("Comment to first post", result.text)
    }

    @Test(expected = PostNotFoundException::class)
    fun commentUnsuccessful() {
        val post1 = Post(
            id = 0,
            date = 13062026,
            text = "First post",
            likes = Likes (0, false, true, true),
            postType = "post"
        )

        WallService.createComment(3, comment = Comment(0, 0,
            23062026, "Comment to the void"))
    }
}