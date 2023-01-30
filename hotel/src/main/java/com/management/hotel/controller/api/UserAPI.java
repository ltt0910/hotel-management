package com.management.hotel.controller.api;

import com.management.hotel.builder.Response;
import com.management.hotel.exception.GroupException;
import com.management.hotel.exception.UserException;
import com.management.hotel.model.request.CreateUserForm;
import com.management.hotel.model.request.UpdateUserForm;
import com.management.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

    @Autowired
    private UserService userServiceImpl;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {

        Response findAllRes;
        try {
            findAllRes = userServiceImpl.findAll();

        } catch (UserException uex) {
            findAllRes = new Response.Builder(1, uex.getCode())
                    .buildMessage(uex.getMessage())
                    .buildData("")
                    .build();
        } catch (Exception e) {
            findAllRes = new Response.Builder(0, HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .buildMessage(e.getMessage())
                    .buildData("")
                    .build();
        }
        return ResponseEntity.ok(findAllRes);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody CreateUserForm user) {

        Response updateRes;

        try {
            updateRes = userServiceImpl.create(user);
        } catch (UserException uex) {
            updateRes = new Response.Builder(1, uex.getCode())
                    .buildMessage(uex.getMessage())
                    .buildData("")
                    .build();
        } catch (GroupException gre) {
            updateRes = new Response.Builder(1, gre.getCode())
                    .buildMessage(gre.getMessage())
                    .buildData("")
                    .build();
        } catch (Exception e) {
            updateRes = new Response.Builder(0, HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .buildMessage(e.getMessage())
                    .buildData("")
                    .build();
        }
        return ResponseEntity.ok(updateRes);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateUserForm form) {
        Response createRes;

        try {
            createRes = userServiceImpl.update(form);
        } catch (UserException uex) {
            createRes = new Response.Builder(1, uex.getCode())
                    .buildMessage(uex.getMessage())
                    .buildData("")
                    .build();
        } catch (GroupException gre) {
            createRes = new Response.Builder(1, gre.getCode())
                    .buildMessage(gre.getMessage())
                    .buildData("")
                    .build();
        } catch (Exception e) {
            createRes = new Response.Builder(0, HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .buildMessage(e.getMessage())
                    .buildData("")
                    .build();
        }
        return ResponseEntity.ok(createRes);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {

        Response createRes;
        try {
            createRes = userServiceImpl.delete(id);
        } catch (UserException uex) {
            createRes = new Response.Builder(1, uex.getCode())
                    .buildMessage(uex.getMessage())
                    .buildData("")
                    .build();
        } catch (Exception e) {
            createRes = new Response.Builder(0, HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .buildMessage(e.getMessage())
                    .buildData("")
                    .build();
        }
        return ResponseEntity.ok(createRes);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        Response res;

        try {
            res = userServiceImpl.findById(id);
        } catch (UserException uex) {
            res = new Response.Builder(1, uex.getCode())
                    .buildMessage(uex.getMessage())
                    .buildData("")
                    .build();
        } catch (Exception e) {
            res = new Response.Builder(0, HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .buildMessage(e.getMessage())
                    .buildData("")
                    .build();
        }

        return ResponseEntity.ok(res);
    }

    @GetMapping("/groupid/{id}")
    public ResponseEntity<?> findByGroupId(@PathVariable Long id) {

        Response res;

        try {
            res = userServiceImpl.findByGroupId(id);
        } catch (GroupException uex) {
            res = new Response.Builder(1, uex.getCode())
                    .buildMessage(uex.getMessage())
                    .buildData("")
                    .build();
        } catch (Exception e) {
            res = new Response.Builder(0, HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .buildMessage(e.getMessage())
                    .buildData("")
                    .build();
        }

        return ResponseEntity.ok(res);
    }

    @GetMapping("/getusername")
    public String getUsername() {

        String res;
        res = userServiceImpl.getUsername();

        return res;
    }

}
