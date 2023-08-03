package com.freddywin.youtubemusic

class DatosArtistas {

    val cancionesAEscucharList = arrayListOf<ClaseCanciones>()
    val categoriasList = arrayListOf<String>()


    init {
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Master Of Pupets",
                "Metallica",
                "https://i1.sndcdn.com/artworks-000012002480-qainwa-t500x500.jpg",
                "Escuchar"
            )
        )
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Valiente",
                "Koko",
                "https://i.scdn.co/image/ab67616d0000b273128e8eb77d1983bab4a64bdb",
                "Escuchar"
            )
        )
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Tu Geografia",
                "Indios",
                "https://i.discogs.com/aTnWYbmjcf04Vm0XUO8EHtD1Di1ZQVXJLl7WHChfcH0/rs:fit/g:sm/q:90/h:500/w:500/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTc4OTQ4/MjAtMTQ1MTE0ODM5/My0yMDczLmpwZWc.jpeg",
                "Escuchar"
            )
        )
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Beneficios",
                "La maquina de camaleon",
                "https://i.discogs.com/Oxe2Tuq35shmq4ORn3j0TlDec8PDXtFoY_Xd7wAZTAs/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTExMTgy/MjAwLTE1MTEzODMz/MDUtNjg4Ni5wbmc.jpeg",
                "Escuchar"
            )
        )
        cancionesAEscucharList.add(
            ClaseCanciones(
                "No te Apartes de Mi",
                "Vicentino",
                "https://cdns-images.dzcdn.net/images/cover/d2e160933ed321af7d92f2c2a4f6568a/500x500.jpg",
                "Escuchar"
            )
        )

        //Canciones a escchar Otra vez--------------------------------
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Que nos paso?",
                "Drims",
                "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/56/aa/1b/56aa1b39-14bd-bc82-8359-f305a197788f/19UMGIM68850.rgb.jpg/1200x1200bf-60.jpg",
                "Otra Vez"
            )
        )

        cancionesAEscucharList.add(
            ClaseCanciones(
                "Beneficios",
                "La maquina de camaleon",
                "https://i.discogs.com/Oxe2Tuq35shmq4ORn3j0TlDec8PDXtFoY_Xd7wAZTAs/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTExMTgy/MjAwLTE1MTEzODMz/MDUtNjg4Ni5wbmc.jpeg",
                "Otra Vez"
            )
        )

        cancionesAEscucharList.add(
            ClaseCanciones(
                "Lost",
                "Linkin Park",
                "https://ts13.tarafdari.com/contents/user300048/content-sound/lost-lyrics-linkin-park.jpg",
                "Otra Vez"
            )
        )

        //Canciones Albumnes destacados--------------------------------
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Back In Black",
                "AC/DC",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/ACDC_Back_in_Black_cover.svg/1200px-ACDC_Back_in_Black_cover.svg.png",
                "Albumes Destacados"
            )
        )
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Meteora",
                "Linkin Park",
                "https://upload.wikimedia.org/wikipedia/en/0/0c/Linkin_Park_Meteora_Album_Cover.jpg",
                "Albumes Destacados"
            )
        )
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Cancion Animal",
                "Soda Stereo",
                "https://sodastereo.com/files/albums/cancion-animal.jpg",
                "Albumes Destacados"
            )
        )
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Seven Son Seventh",
                "Iron Mainden",
                "https://images.squarespace-cdn.com/content/v1/565c1ab5e4b05079e4bfa169/1661152471054-VD3LIEDZYYW2ZDWJLUNV/Iron+Maiden+Seventh+Son+Of+A+Seventh+Son+Album+Cover.jpg?format=2500w",
                "Albumes Destacados"
            )
        )
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Back In Black ",
                "AC/DC",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/ACDC_Back_in_Black_cover.svg/1200px-ACDC_Back_in_Black_cover.svg.png",
                "Albumes Destacados"
            )
        )
        cancionesAEscucharList.add(
            ClaseCanciones(
                "99%",
                "Ska-p",
                "https://www.ska-p.com/wp-includes/images/webimages/Caratula-99.jpg",
                "Albumes Destacados"
            )
        )

        //Mixes para ti------------
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Canciones de los 70",
                "Mix 1",
                "https://hips.hearstapps.com/hmg-prod/images/mejores-discos-de-la-historia-del-pop-1629463038.jpg?crop=0.502xw:1.00xh;0.250xw,0&resize=1200:*",
                "MixParaTi"
            )
        )

        cancionesAEscucharList.add(
            ClaseCanciones(
                "Canciones de los 90",
                "Mix 2",
                "https://cadenaser.com/resizer/TRWBPR0mV-hzEOd69h73IKFUaM4=/736x552/filters:format(jpg):quality(70)/cloudfront-eu-central-1.images.arcpublishing.com/prisaradio/Y7N5PE7O55I7FDJJTY6NLRB3UA.jpg",
                "MixParaTi"
            )
        )

        cancionesAEscucharList.add(
            ClaseCanciones(
                "Canciones de los 2000",
                "Mix 3",
                "https://static.vecteezy.com/system/resources/previews/007/619/838/non_2x/vinyl-disc-record-for-music-album-cover-design-vector.jpg",
                "MixParaTi"
            )
        )

        cancionesAEscucharList.add(
            ClaseCanciones(
                "Artistas Varios",
                "Mix 4",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMy2wEgc2a5cmYlzT1ff8i3N9CuEb-sRjbXFs26vbz_9JXcjDXWvQaxrj_yxJBhkD9fFY&usqp=CAU",
                "MixParaTi"
            )
        )

        //Artistas Mas Escuchadas--------------------
        cancionesAEscucharList.add(
            ClaseCanciones(
                "The Strokes",
                "2000",
                "https://imagenes.elpais.com/resizer/0pdhn03o3xmKDRUoE0kFz8VDpt8=/1960x1470/cloudfront-eu-central-1.images.arcpublishing.com/prisa/FRFD6A6SASOOUFZD5QY4IJAN6U.jpg",
                "ArtistasMasEscuchadas"
            )
        )
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Bullet For My Valentine",
                "2001",
                "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/65b3dd22-b699-4b4f-9a4f-dce59ca5627a/dnfgaa-0ac372f4-2e4f-4e01-a628-6a20d571f930.jpg/v1/fill/w_1017,h_786,q_70,strp/bullet_for_my_valentine_logo_by_raysic08_dnfgaa-pre.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9MTE2NiIsInBhdGgiOiJcL2ZcLzY1YjNkZDIyLWI2OTktNGI0Zi05YTRmLWRjZTU5Y2E1NjI3YVwvZG5mZ2FhLTBhYzM3MmY0LTJlNGYtNGUwMS1hNjI4LTZhMjBkNTcxZjkzMC5qcGciLCJ3aWR0aCI6Ijw9MTUwOCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.1exGlnWPxALYjmW4AUQIi8LhGGCmEeswW-rszldzdXs",
                "ArtistasMasEscuchadas"
            )
        )
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Gustavo Cerati",
                "1082-2014",
                "https://openseauserdata.com/files/a40eec3fdb0d44f271fb72eacc3984bd.jpg",
                "ArtistasMasEscuchadas"
            )
        )
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Que nos paso?",
                "Drims",
                "https://ismorbo.com/wp-content/uploads/2020/04/the-strokes-the-new-abnormal-1-1068x1068-1.jpeg",
                "ArtistasMasEscuchadas"
            )
        )
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Tarja Turunen",
                "1996",
                "https://peru21.pe/resizer/wZClaRe3sXPjav_RhyqBDnEQ7Rc=/1200x900/smart/filters:format(jpeg):quality(75)/arc-anglerfish-arc2-prod-elcomercio.s3.amazonaws.com/public/V33IRFOWRJFWDC2IQUVTEX4AGI.jpg",
                "ArtistasMasEscuchadas"
            )
        )


        //Categoria para listas recoemdas--------------
        cancionesAEscucharList.add(
            ClaseCanciones(
                "Rock And Roll",
                "1960",
                "https://1.bp.blogspot.com/-I9p_SmQruvk/WjFoFx61JsI/AAAAAAAAObE/CxFb9wQFPAA232TbRPah1zc-QCde5JVjwCLcBGAs/s320/Las-mejores-bandas-de-la-historia.jpg",
                "ListasRecomendadas"
            )
        )

        cancionesAEscucharList.add(
            ClaseCanciones(
                "The Beatles",
                "1960",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLKG_2nK3g4DpOE0e9RIEdehTtT6cLzOAqL5QD081d4kKAsXd73zpZ6DvLOh7MnelZlYs&usqp=CAU",
                "ListasRecomendadas"
            )
        )

        cancionesAEscucharList.add(
            ClaseCanciones(
                "Pink Floyd",
                "1966",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/The_Dark_Side_of_the_Moon_Cover.svg/250px-The_Dark_Side_of_the_Moon_Cover.svg.png",
                "ListasRecomendadas"
            )
        )

        // Categorías a la lista de categorías
        categoriasList.add("Escuchar")
        categoriasList.add("Otra Vez")
        categoriasList.add("Albumes Destacados")
        categoriasList.add("MixParaTi")
        categoriasList.add("ArtistasMasEscuchadas")
        categoriasList.add("ListasRecomendadas")
    }

    // Método para generar el mapa de canciones por categoría
    fun generarCategoriasArtistasList(): List<CategoriaArtistas> {
        val categoriasArtistasList = mutableListOf<CategoriaArtistas>()

        for (categoria in categoriasList) {
            val artistasEnCategoria = mutableListOf<ClaseCanciones>()
            for (artista in cancionesAEscucharList) {
                // Aquí debes implementar la lógica para verificar si el artista pertenece a la categoría actual
                if (artista.categoria == categoria) {
                    artistasEnCategoria.add(artista)
                }
            }
            categoriasArtistasList.add(CategoriaArtistas(categoria, artistasEnCategoria))
        }
        return categoriasArtistasList
    }
}
