package br.com.alura.challanger.configuration.securyt;

import br.com.alura.challanger.entity.Usuario;
import br.com.alura.challanger.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;
import java.awt.*;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private UsuarioRepository repository;


    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }
    /**
     * metodo responsavel por validar o token
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);
        if(tokenService.isTokenValido(token)){
            autenticarCliente(token);
        }


        filterChain.doFilter(request,response);
    }

    private void autenticarCliente(String token) {
        String idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = repository.findById(idUsuario).orElseThrow(() -> new BadRequestException("id usuario não encontrado!!"));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getPerfis());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * metodo responsavel por recuperar o Token no header
     * @param request
     * @return
     */
    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
