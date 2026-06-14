data class Post(
    val id: Int,
    val ownerId: Int = 1,
    val fromId: Int = 1,
    val createdBy: Int = 0,
    val date: Int,
    val text: String,
    val likes: Likes,
    val postType: String, // может быть: post, copy, reply, postpone, suggest
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isFavourite: Boolean = false
)

class Likes(
    count: Int = 0,
    userLikes: Boolean = false,
    canLike: Boolean = true,
    canPublish: Boolean = true
) {
    var count: Int = count
        set (value) {
            if (value >= 0) field = value
        }
}

object WallService {
    private var posts = emptyArray<Post>()
    private var postId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++postId, date = 13062026, postType = "post")
        return posts.last()
    }

    fun print() {
        for (post in posts) {
            println(post)
        }
    }

    fun update(id: Int): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] = post.copy(text = "Updated text")
                println(posts[index])
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        postId = 0
    }
}

fun main() {
    WallService.add(Post(0, 1, 1, 0, 13062026, "First post",
        likes = Likes(0, false, true, true), "post",
        false, false, false))
    WallService.add(Post(0, 1, 1, 0, 13062026, "Second post",
        likes = Likes(0, false, true, true), "post",
        false, false, false))
    WallService.print()
    println()
    WallService.update(1)
    println(WallService.update(3))
}