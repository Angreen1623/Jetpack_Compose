package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import android.content.res.Configuration
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme{
                //Chama o método "conversation" #106 abaixo, ultilizando os parametros a seguir "SampleData.conversationSample" #114
                Conversation(SampleData.conversationSample)
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message){
        //Ordena o conteúdo criado em forma de linha
    Row(modifier = Modifier.padding(all = 8.dp)){
            //Cria uma Imagem
        Image(
                //Pinta a imagem usando os recursos ditados no painerResource
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
                //Modifica e edita a aparencia da imagem
            modifier = Modifier
                    //modifica o tamanho
                .size(40.dp)
                    //modifica o formato
                .clip(CircleShape)
                    //cria uma borda ao conteudo
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
            //adiciona um espaçamento como uma div vasia entre um conteudo e outro
        Spacer(modifier = Modifier.width(8.dp))

            //colore quando clica no conteudo
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )
            //Ordena o conteúdo criado em forma de Coluna
        Column (modifier = Modifier.clickable { isExpanded = !isExpanded}) {
            Text(
                    //seta o texto da mensagem
                text = msg.author,
                    //seta a cor da mensagem usando o caminho do Theme.kt e as cores de Color.kt
                color = MaterialTheme.colorScheme.secondary,
                    //modifica o etilo da mensagem, no caso o tamanho
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp, color = surfaceColor, modifier = Modifier.animateContentSize().padding(1.dp)) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                        //Quebra de texto
                    maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium

                )
            }
        }
    }
}

@Preview(name="Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name="Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    Surface {
        MessageCard(
            msg = Message("André", "Hey, take a look at Jetpack Compose, it's great!")
        )
    }
}



@Composable
fun Conversation(messages: List<Message>){
    //List<> é uma lista Mutável
    LazyColumn{
        items(messages) {message->
            MessageCard(message)
            //ultilizando a função messageCard #48 ele
        }
    }
    //abre o object chamado "SampleData" #114, puxando a variavel conversationSaple ultilizando o metodo MessageCard #48
}
object SampleData{
    val conversationSample = listOf(
        //listOf é uma Lista de variaveis imutavies
        Message("André","Para de falar André D:<"),
        Message("André","Cala a boca vc bobão!!!!"),
        Message("André","Oxe, ta doido de falar esse tipo de coisa para"+
                " mim?"),
        Message("André","De você eu falo o que eu quero"),
        Message("André","cabeça de abóbora, cabeça-de-alho-chôcho, cabeça-" +
                "de-vento, cabeça no ar, cabeça oca, cabeçudo, cabotino, cabra, cabrão, " +
                "cábula, caceteiro, cachorro, cacique, caco, cadela, caga-leite, caga-tacos" +
                ", cagão, caguinchas, caixa de óculos, calaceiro, calão, calhandreira, calhor" +
                "das, calinas, caloteiro, camafeu, camelo, campónio, canalha, canastrão, cand" +
                "ongueiro, cão, caquética, cara-de-cu-à-paisana, caramelo, carapau de corrida, c" +
                "areca, careta, carniceiro, carraça, carrancudo, carroceiro, casca grossa, cas" +
                "murro, cavalgadura, cavalona, cegueta, celerado, cepo, chalado, chanfrado, cha" +
                "rlatão, chatarrão, chato, chauvinista, chibo, chico-esperto, chifrudo, choné," +
                " choninhas, choramingas, chulo, chunga, chupado das carochas, chupista, cigano" +
                ", cínico, cobarde, cobardolas, coirão, comuna, cona-de-sabão, convencido, c" +
                "opinho de leite, corcunda, corno, cornudo, corrupto, coscuvilheira, coxo, crá" +
                "pula, cretino, cromo, cromaço, criminoso, cunanas, cusca")
    )
    //ultilizando a função Message dentro de messageCard #48 ele cria uma troca de conversas semelhantes a
    //um aplicativo de conversas
}

@Preview
@Composable
fun PreviewConversation(){
    MyApplicationTheme{
        Conversation(SampleData.conversationSample)
        //abre o object chamado "SampleData" #114, puxando a variavel conversationSaple #115
    }
}
