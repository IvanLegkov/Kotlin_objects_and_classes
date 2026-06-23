import java.io.File

data class Post(
    val id: Int,
    val ownerId: Int = 1,
    val fromId: Int = 1,
    val createdBy: Int = 0,
    val date: Int,
    val text: String?,
    val likes: Likes,
    val postType: String?, // может быть: post, copy, reply, postpone, suggest
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isFavourite: Boolean = false,
    val attachments: Array<Attachment> = emptyArray()
)

interface Attachment {
    val type: String // может быть: photo, video, audio, file, sticker
}

class PhotoAttachment(
    override val type: String = "photo",
    val photo: Photo
) : Attachment

class Photo(
    val id: Int,
    val ownerId: Int,
    val text: String,
    val date: Int
)

class AudioAttachment(
    override val type: String = "audio",
    val audio: Audio
) : Attachment

class Audio(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val duration: Int
)

class VideoAttachment(
    override val type: String = "video",
    val video: Video
) : Attachment

class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val duration: Int
)

class FileAttachment(
    override val type: String = "file",
    val file: File
) : Attachment

class File(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int
)

class StickerAttachment(
    override val type: String = "sticker",
    val sticker: Sticker
) : Attachment

class Sticker(
    val productId: Int,
    val stickerId: Int,
    val animationUrl: String,
    val isAllowed: Boolean
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

class Comment(
    val id: Int,
    val ownerId: Int,
    val date: Int,
    val text: String
)

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
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

    fun createComment(postId: Int, comment: Comment): Comment {
        for ((index, post) in posts.withIndex()) {
            if (post.id == postId) {
                comments += comment
                return comment
            }
        }
        throw PostNotFoundException("Such post doesn't exist")
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

    println(WallService.createComment(1, comment = Comment(0, 0,
        23062026, "Comment to first post")))
}

class PostNotFoundException(message: String): RuntimeException(message)