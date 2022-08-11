package com.hua.github_app.image

/**
 * Created on 2022/8/10.
 * Abstraction of image loading, shield specific image loading library
 *
 * @author hua
 */
interface ILoader {
    /**
     * Use the data in [options] to load the image, such as [RequestOptions.url]
     * and [RequestOptions.errorResId].
     * The loader implementer needs to distribute the loaded results to the [target].
     */
    fun load(options: RequestOptions, target: ITarget)
}